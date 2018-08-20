package com.adc.da.main.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.log.constant.LogConstants;
import com.adc.da.log.entity.LogEO;
import com.adc.da.log.service.LogEOService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.utils.UUID;

/**
 * 用于记录调用service层日志
 */
@Aspect
@Component
public class BusinessLogAspect {
	private static Logger logger = LoggerFactory.getLogger(BusinessLogAspect.class);

	@Autowired
	private LogEOService logEOService;

	@Autowired
	private UserEODao userEODao;

	@Value("${sysLogType}")
	private String sysLogType;

	/**
	 * 匹配Service层的save, update, delete, get, find, page等方法
	 */
	@Pointcut(value = "(execution(* com.adc.da.*.service.*.save*(..)) "
			+ "|| execution(* com.adc.da.*.service.*.update*(..)) "
			+ "|| execution(* com.adc.da.*.service.*.delete*(..)) " + "|| execution(* com.adc.da.*.service.*.get*(..)) "
			+ "|| execution(* com.adc.da.*.service.*.find*(..)) " + "|| execution(* com.adc.da.*.service.*.page*(..))) "
			+ "&& !execution(* com.adc.da.log.service.LogEOService.*(..))")
	private void servicePointcut() {
	}

	@Around(value = "servicePointcut()")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		Class cls = joinPoint.getTarget().getClass();
		String signature = joinPoint.getSignature().getName();
		Object result;
		// 调用service层开始时间
		long startTime = System.currentTimeMillis();
		result = joinPoint.proceed();
		// 调用service层结束时间
		long endTime = System.currentTimeMillis();
		// dev模式下不记系统日志
		if (LogConstants.LOG_TYPE_DEV.equalsIgnoreCase(sysLogType)) {
			return result;
		}
		String userId = UserUtils.getUserId();
		// 非登录模式下不记系统日志
		if (userId == null) {
			return result;
		}
//		logger.debug("业务日志组件开始工作");
		if (LogConstants.LOG_TYPE_SYS.equalsIgnoreCase(sysLogType)) {
			writeLog(cls.getName(), signature, null, userId, startTime, endTime);
		} else {
			for (Method method : cls.getDeclaredMethods()) {
				BusinessLog logAnnotation = (BusinessLog) method.getAnnotation(BusinessLog.class);
				if (logAnnotation != null) {
					String methodName = method.getName();
					if (signature.equals(methodName)) {
						writeLog(cls.getName(), signature, logAnnotation.description(), userId, startTime, endTime);
					}
				}
			}
		}
//		logger.debug("业务日志组件工作结束");
		return result;
	}

	private void writeLog(String className, String methodName, String logAnnotation, String userId, long startTime, long endTime) throws Exception {
		LogEO logEO = new LogEO();
		logEO.setId(UUID.randomUUID10());
		logEO.setClassName(className);
		logEO.setMethod(methodName);
		if (logAnnotation != null && !"".equals(logAnnotation)) {
			logEO.setDescription(logAnnotation);
		}
		logEO.setUsid(userId);
		UserEO userEO = userEODao.selectByPrimaryKey(userId);
		if (userEO != null) {
			logEO.setAccount(userEO.getAccount());
		}
		logEO.setStartTime(new Date(startTime));
		logEO.setEndTime(new Date(endTime));
		logEOService.insertSelective(logEO);
	}
}