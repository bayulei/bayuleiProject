package com.adc.da.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.util.utils.PublicKeyMap;
import com.adc.da.util.utils.RSAUtils;

@RestController
@RequestMapping("/${restPath}/sys/rsa")
public class RsaSecurityController {

	@GetMapping("/modulus/exponent")
	public PublicKeyMap generateModulusAndExponent(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap(sessionId);
		if (publicKeyMap == null) {
			RSAUtils.generateKeyPair(sessionId);
			publicKeyMap = RSAUtils.getPublicKeyMap(sessionId);
		}
		return publicKeyMap;
	}

}
