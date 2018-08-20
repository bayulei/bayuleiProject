package com.adc.da.login.security;

import com.adc.da.sys.entity.UserEO;
import com.adc.da.login.rest.LoginRestController;
import com.adc.da.login.security.exception.CaptchaException;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统安全认证实现类
 */
@Service("systemAuthorizingRealm")
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);
    
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    
    /** 2018年8月15日  增加验证码模式 默认1不开启  2:必须验证 3 登录失败后验证 **/
/*    @Value("${verifyCodeMode:1}")
	private int verifyCodeMode;	// 验证码模式
*/    
    private UserEOService userService;
    
    public UserEOService getUserService() {
    	if(userService == null) {
    		userService = SpringContextHolder.getBean(UserEOService.class);
    	}
    	return userService;
    }

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //验证码验证
        doVerifyCode(token);
        /** 验证码验证规则 **/
/*    	if (verifyCodeMode == 2) {
			doVerifyCode(token);
		} 
		else if (verifyCodeMode == 3) {
			// 如果登录失败超过3次需要验证码
			if (LoginRestController.isNeedValidCode(token.getUsername())) {
				doVerifyCode(token);
			}
		}*/
        UserEO user = getUserService().getUserByLoginName(token.getUsername());
        if (user == null) {
            return null;
        }

        byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
        return new SimpleAuthenticationInfo(new Principal(user), user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        UserEO user = userService.getUserByLoginName(principal.getLoginName());
        if (user != null) {
            try {
				return UserUtils.getAuthInfo();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else {
            return null;
        }
		return null;
    }

	/**
	 * 验证码验证
	 */
	private void doVerifyCode(UsernamePasswordToken token) {
		Session session = SecurityUtils.getSubject().getSession();
		String verifyCode = (String) session.getAttribute("VerifyCode");
		if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(verifyCode)) {
			session.removeAttribute("VerifyCode");
			throw new CaptchaException("验证码错误.");
		}
		// 测试部要求登录成功也清空验证码
		session.removeAttribute("VerifyCode");
	}
    
    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }


    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id;
        private String loginName;
        private String name;
        private Map<String, Object> cacheMap;

        public Principal(UserEO user) {
            this.id = user.getUsid()==null?"":String.valueOf(user.getUsid());
            this.loginName = user.getAccount();
            this.name = user.getUsname();
        }

        public String getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }

        public Map<String, Object> getCacheMap() {
            if (cacheMap == null) {
                cacheMap = new HashMap<String, Object>();
            }
            return cacheMap;
        }

    }
}
