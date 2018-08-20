package com.adc.da.login.util;

import com.adc.da.sys.entity.MenuEO;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.login.security.SystemAuthorizingRealm;
import com.adc.da.sys.service.MenuEOService;
import com.adc.da.sys.service.RoleEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.ObjectUtils;
import com.adc.da.util.utils.SpringContextHolder;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Map;

public class UserUtils {

    public static final String CURRENT_USER = "currentUser";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";
    public static final String CACHE_MENU_TREE = "menuTree";
    public static final String CACHE_AREA_LIST = "areaList";
    public static final String CACHE_OFFICE_LIST = "officeList";

    private static UserEOService userService = SpringContextHolder.getBean(UserEOService.class);
    private static MenuEOService menuService = SpringContextHolder.getBean(MenuEOService.class);
    private static RoleEOService roleEOService = SpringContextHolder.getBean(RoleEOService.class);

    /**
     * 退出
     */
    public static void logout() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (UnavailableSecurityManagerException e) {
            e.printStackTrace();
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前登录用户ID
     *
     */
    public static String getUserId() {
        String userId = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
            if (principal != null) {
                userId = principal.getId();
            }
        } catch (UnavailableSecurityManagerException e) {
        } catch (InvalidSessionException e) {
        }
        return userId;
    }

    /**
     * 获取当前登录用户信息
     */
    public static UserEO getUser() throws NumberFormatException, Exception {
        UserEO user = (UserEO) CacheUtils.getCache(CURRENT_USER);
        if (user == null) {
            String userId = getUserId();

            if(StringUtils.isNotEmpty(userId)) {
                UserEO userInDb = userService.selectByPrimaryKey(userId);
                user = ObjectUtils.clone(userInDb);
                user.setPassword(null);
                CacheUtils.putCache(CURRENT_USER, user);
            }
        }
        return user;
    }

    public static void updateUserInfo(UserEO userVo) throws NumberFormatException, Exception {
        UserEO user = (UserEO) CacheUtils.getCache(CURRENT_USER);
        if (user != null) {
            String userId = getUserId();

            if (StringUtils.isNotEmpty(userId)) {
                UserEO userInDb = userService.selectByPrimaryKey(userId);
                userInDb.setUsname(userVo.getUsname());
                userService.save(userInDb);

                CacheUtils.removeCache(CURRENT_USER);
            }
        }
    }

    /**
     * 获取当前登录用户角色列表
     */
    public static List<RoleEO> getRoleList() throws NumberFormatException, Exception {
        List<RoleEO> roleList = (List<RoleEO>) CacheUtils.getCache(CACHE_ROLE_LIST);
        if (roleList == null) {
            UserEO user = getUser();
            if(user != null) {
                roleList = roleEOService.getSysRoleListByUserId(user.getUsid());
            }
            CacheUtils.putCache(CACHE_ROLE_LIST, roleList);
        }
        return roleList;
    }

    public static String getRoleIds() throws NumberFormatException, Exception {
        List<RoleEO> roleList = getRoleList();
        if (CollectionUtils.isEmpty(roleList)) {
            return "";
        }
        StringBuilder roleIds = new StringBuilder();
        for (RoleEO sysRoleEO : roleList) {
            roleIds.append(sysRoleEO.getId()).append(",");
        }
        return roleIds.substring(0, roleIds.length() - 1);
    }


    /**
     * 获取当前登录用户菜单列表
     */
    public static List<MenuEO> getMenuList() throws NumberFormatException, Exception {
        List<MenuEO> menuList = (List<MenuEO>) CacheUtils.getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            UserEO user = getUser();
            if (isAdmin(user)) {
                menuList = menuService.findAll();
            } else {
                menuList = menuService.listMenuEOByUserId(String.valueOf(user.getUsid()));
            }
            CacheUtils.putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 获取当前登录用户菜单树
     *
     */
    public static MenuEO getMenuTree() {
        MenuEO menu = (MenuEO) CacheUtils.getCache(CACHE_MENU_TREE);
        if (menu == null) {
//            menu = menuService.organizeListAsTree(menuService.selectByPrimaryKey(1), getMenuList());
            CacheUtils.putCache(CACHE_MENU_TREE, menu);
        }
        return menu;
    }

    /**
     * 判断用户是否是超级管理员
     * @param userVo
     * @return
     */
    public static boolean isAdmin(UserEO userVo) {
        //todo
        return userVo != null && userVo.getUsid().equals("1");
    }

    /**
     * 获取用户菜单权限信息
     */
    public static SimpleAuthorizationInfo getAuthInfo() throws NumberFormatException, Exception {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<MenuEO> list = UserUtils.getMenuList();
        for (MenuEO menu : list) {
            if (StringUtils.isNotBlank(menu.getPermission())) {
                // 添加基于Permission的权限信息
                for (String permission : StringUtils.split(menu.getPermission(), ",")) {
                    info.addStringPermission(permission);
                }
            }
        }
        return info;
    }

    public static void flush() {
        CacheUtils.removeCache(CURRENT_USER);
        CacheUtils.removeCache(CACHE_MENU_LIST);
        CacheUtils.removeCache(CACHE_MENU_TREE);
    }


    private static final class CacheUtils {
        public static Object getCache(String key) {
            return getCache(key, null);
        }

        public static Object getCache(String key, Object defaultValue) {
            Object obj = getCacheMap().get(key);
            return obj == null ? defaultValue : obj;
        }

        public static void putCache(String key, Object value) {
            getCacheMap().put(key, value);
        }

        public static void removeCache(String key) {
            getCacheMap().remove(key);
        }

        public static Map<String, Object> getCacheMap() {
            Map<String, Object> map = Maps.newHashMap();
            try {
                Subject subject = SecurityUtils.getSubject();
                SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
                return principal != null ? principal.getCacheMap() : map;
            } catch (UnavailableSecurityManagerException e) {

            } catch (InvalidSessionException e) {

            }
            return map;
        }
    };

}
