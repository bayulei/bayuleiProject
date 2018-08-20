package com.adc.da.sys.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import com.adc.da.sys.entity.UserEO;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

public class LoginUserUtil {
	/**
     * 获取当前登录用户ID
     *
     */
    public static String getUserId() {
        String userId = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            Object object=subject.getPrincipal();
            if(object!=null){
            	String json = JSONObject.toJSONString(object);
            	if(json!=null && json.length()>0){
            		Map<String, Object> userInfo=JSONObject.parseObject(json, Map.class);
            		userId=userInfo.get("id").toString();
            	}
            }
        } catch (UnavailableSecurityManagerException e) {
        } catch (InvalidSessionException e) {
        }
        return userId;
    }
}
