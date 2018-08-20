package com.adc.da.login.rest;

import com.adc.da.sys.entity.MenuEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.login.security.UsernamePasswordToken;
import com.adc.da.login.security.exception.CaptchaException;
import com.adc.da.login.security.validatecode.IVerifyCodeGen;
import com.adc.da.login.security.validatecode.SimpleCharVerifyCodeGenImpl;
import com.adc.da.login.security.validatecode.VerifyCode;
import com.adc.da.login.util.CacheUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.PasswordUtils;
import com.adc.da.util.utils.RequestUtils;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping(value = "${restPath}/")
@Api(description = "登录接口")
public class LoginRestController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    /**
     * 10分钟内最大错误次数
     */
    private static final int MAX_LOGIN_ERROR_COUNT = 3;

    @Autowired
    private UserEOService userService;

    @Autowired
    BeanMapper beanMapper;

    @Value("${shiro.session}")
    private  Long timeOut;

    /**
     * 4位验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28, 4);
            request.getSession().setAttribute("VerifyCode", verifyCode.getCode());
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 6位验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/verifyCode6")
    public void verifyCode6(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(100, 28, 6);
            request.getSession().setAttribute("VerifyCode", verifyCode.getCode());
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "登录")
//    @GetMapping(value = "/login")
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseMessage loginRest(HttpServletRequest request,
                                     @RequestParam @NotNull(message = "请输入账号") String username,
                                     @RequestParam @NotNull(message = "请输入密码") String password,
                                     @RequestParam(value = "isRememberMe", defaultValue = "false") Boolean isRememberMe,
                                     String verifyCode) {
        Subject subject = SecurityUtils.getSubject();
        SecurityUtils.getSubject().getSession().setTimeout(timeOut*1000);
        UserEO userEO;
        // 前台如果base64传输密文，则需要解码
        //username = new String(Encodes.decodeBase64(username));
        password = new String(Encodes.decodeBase64(password));
        username = username.trim();
        password = password.trim();
            try {

                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray(), verifyCode);
                subject.login(usernamePasswordToken);

                userEO = UserUtils.getUser();
                List<MenuEO> menuList = UserUtils.getMenuList();
                request.getSession().setAttribute(RequestUtils.LOGIN_USER, userEO);
                request.getSession().setAttribute(RequestUtils.LOGIN_USER_ID, UserUtils.getUserId());
                request.getSession().setAttribute(RequestUtils.LOGIN_ROLE_ID, UserUtils.getRoleIds());
                List<UserEO> getUser =userService.queryOrgByAccount(username);
                
                if(getUser.size() == 0){
                    return Result.error("登录失败！账号无组织机构，为禁用状态");
                }
                if(menuList==null || menuList.isEmpty()){
                	return Result.error("登录失败！账号无访问权限，请联系管理员授权");
                }
                return Result.success(beanMapper.map(userEO, UserVO.class));
            } catch (CaptchaException e) {
                logger.info("验证码验证失败");
                return Result.error("r0012", "您输入的验证码不正确");
            } catch (UnknownAccountException e) {
                increaseLoginErrorCount(username);
                logger.info("用户[{}]身份验证失败", username);
                boolean isNeedValidCode = isNeedValidCode(username);
                return Result.error("r0011", "您输入的账号或密码有误", isNeedValidCode);
            } catch (IncorrectCredentialsException e) {
                increaseLoginErrorCount(username);
                logger.info("用户[{}]密码验证失败", username);
                return Result.error("r0011", "您输入的账号或密码有误");
            } catch (AuthenticationException e) {
                // 记录日志，有未处理的验证失败
                logger.error(e.getMessage(), e);
                return Result.error("r0013", e.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return Result.error("r0013", e.getMessage());
            }
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseMessage logout() {
        UserUtils.logout();
        return Result.success();
    }


    /**
     * 登录成功之后获取当前登录用户信息的接口
     */
    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseMessage<UserEO> userInfo(HttpServletResponse response) throws NumberFormatException, Exception {
        UserEO user = UserUtils.getUser();
        if (user != null) {
            return Result.success(user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error();
        }
    }

    @ApiOperation(value = "获取登录用户菜单权限")
    @GetMapping("/userMenu")
    @ResponseBody
    public ResponseMessage<MenuEO> userMenu() {
        return Result.success(UserUtils.getMenuTree());
    }

    @ApiOperation(value = "修改当前用户信息")
    @PutMapping("/updateUserInfo")
    @ResponseBody
    public ResponseMessage updateUserInfo(@RequestBody UserEO userEO) throws NumberFormatException, Exception {
        UserUtils.updateUserInfo(userEO);
        return Result.success(UserUtils.getUser());
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePassword")
    @RequiresPermissions("login:login:changePassowrd")
    @ResponseBody
    public ResponseMessage updatePassword(@RequestParam(value="oldPassword",required=false) String oldPassword,
                                          @NotNull(message = "请输入新密码") @RequestParam String newPassword) {
        // 前台如果base64传输密文，则需要解码
        /*oldPassword = new String(Encodes.decodeBase64(oldPassword));*/
        newPassword = new String(Encodes.decodeBase64(newPassword));
        userService.updatePassword(UserUtils.getUserId(), oldPassword,PasswordUtils.encryptPassword(newPassword));
        return Result.success();
    }


    /**
     * 判断是否需要验证验证码
     *
     * @param userName
     * @return
     */
    public static boolean isNeedValidCode(String userName) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }

        Integer loginFailNum = loginFailMap.get(userName);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }

        return loginFailNum >= MAX_LOGIN_ERROR_COUNT;
    }

    /**
     * 登录失败次数增加一次
     *
     * @param userName
     * @return
     */
    public static void increaseLoginErrorCount(String userName) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(userName);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }

        loginFailNum++;
        loginFailMap.put(userName, loginFailNum);
    }

    @ApiOperation(value = "修改密码验证原始密码")
    @GetMapping("/verificationPasswordBefore")
    @ResponseBody
    public ResponseMessage verificationPasswordBefore(String password_before) throws Exception {
         UserEO result =  UserUtils.getUser();
         UserEO userEO = userService.selectByUnameAndPwd(result);
         if(PasswordUtils.validatePassword(password_before,userEO.getPassword())){
             return Result.success();
         }
         else{
             return Result.error("fail","原始密码错误！");
         }
    }
}
