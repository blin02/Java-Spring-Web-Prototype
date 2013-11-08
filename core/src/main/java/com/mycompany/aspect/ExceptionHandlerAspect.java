package com.mycompany.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.util.ExceptionUtil;

/**
 * Aspect class that handles exceptions
 *
 */
@Aspect
public class ExceptionHandlerAspect {	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Handles exceptions throw from the Service layer. Stack trace will be logged.
	 * @param joinPoint join point
	 * @param e exception variable
	 */
	@AfterThrowing(pointcut = "execution(* com.mycompany.service.*.*(..))", throwing = "e")
	public void handleException(JoinPoint joinPoint, Throwable e) {		
		//String exceptionSource = joinPoint.getTarget().getClass().getName() + "." +joinPoint.getSignature().getName();
		String stackTrace = ExceptionUtil.getStackTrace(e);		
		log.error(stackTrace);
	}

}
