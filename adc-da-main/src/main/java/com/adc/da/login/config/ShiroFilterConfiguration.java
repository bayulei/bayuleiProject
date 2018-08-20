package com.adc.da.login.config;

import com.adc.da.login.security.FormAuthenticationFilter;
import com.adc.da.util.constant.GlobalConfig;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroFilterConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private SecurityManager securityManager;

    @PostConstruct
    public void init() {
        GlobalConfig.setAdminPath(env.getProperty("adminPath"));
        GlobalConfig.setRestApiPath(env.getProperty("restPath"));
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        String adminPath = GlobalConfig.getAdminPath();
        String restPath = GlobalConfig.getRestApiPath();

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(adminPath + "/login");
        shiroFilterFactoryBean.setSuccessUrl(adminPath);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new FormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put(restPath + "/login", "anon");
        filterChainDefinitionMap.put(restPath + "/logout", "anon");
        filterChainDefinitionMap.put(restPath + "/verifyCode", "anon");
        filterChainDefinitionMap.put(restPath + "/userInfo", "anon");
        filterChainDefinitionMap.put(restPath + "/userMenu", "anon");
        filterChainDefinitionMap.put(restPath + "/updateUserInfo", "anon");
        filterChainDefinitionMap.put(restPath + "/updatePassword", "anon");
        filterChainDefinitionMap.put(restPath + "/user/supplierRegistry/*", "anon");
        filterChainDefinitionMap.put("/api/**", "authc");
        filterChainDefinitionMap.put("/app/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/userfiles/**", "anon");
        filterChainDefinitionMap.put(adminPath + "/login", "authc");
        filterChainDefinitionMap.put(adminPath + "/logout", "logout");
        filterChainDefinitionMap.put(adminPath + "/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

}
