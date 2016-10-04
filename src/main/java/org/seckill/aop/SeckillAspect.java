package org.seckill.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.seckill.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SeckillAspect {

	private Logger logger = LoggerFactory.getLogger(SeckillAspect.class);

	@Pointcut("execution(* org.seckill.web.SeckillController.execute(..))")
	public void seckillpt() {}

	@Before("seckillpt()")
	public void beforeSeckill(JoinPoint joinPoint) {
		System.out.println("==========执行controller前置通知===============");
		if (logger.isInfoEnabled()) {
			logger.info("*********秒杀开始之前*********");
			logger.info("before" + joinPoint);
		}
	}

	@Around("seckillpt()")
	public void around(ProceedingJoinPoint pjp) {
		System.out.println("==========开始执行controller环绕通知===============");
		long start = System.currentTimeMillis();
		try {
			pjp.proceed();
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled()) {
				logger.info("around " + pjp + "\tUse time : " + (end - start) + " ms!");
			}
			System.out.println("==========结束执行controller环绕通知===============");
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled()) {
				logger.info(
						"around " + pjp + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
			}
		}

	}

	@After("seckillpt()")
	public void afterSeckill(JoinPoint joinPoint) {
		/*
		 * HttpServletRequest request =
		 * ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes(
		 * )).getRequest(); HttpSession session = request.getSession();
		 */
		// 读取session中的用户
		// User user = (User) session.getAttribute("user");
		// 请求的IP
		// String ip = request.getRemoteAddr();

		if (logger.isInfoEnabled()) {
			logger.info("*********秒杀结束之后*********");
			logger.info("before" + joinPoint);
		}
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						operationType = method.getAnnotation(Log.class).operationType();
						operationName = method.getAnnotation(Log.class).operationName();
						break;
					}
				}
			}
			System.out.println("=====controller后置通知开始=====");
			System.out.println("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")
					+ "." + operationType);
			System.out.println("方法描述:" + operationName);
			// TODO 持久化到数据库
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==后置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	@AfterReturning("seckillpt()")
	public void afterReturn(JoinPoint joinPoint) {
		System.out.println("=====执行controller后置返回通知=====");
		if (logger.isInfoEnabled()) {
			logger.info("afterReturn " + joinPoint);
		}
	}

	@AfterThrowing(pointcut = "seckillpt()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		/*
		 * HttpServletRequest request =
		 * ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes(
		 * )).getRequest(); HttpSession session = request.getSession();
		 */
		// 读取session中的用户
		// User user = (User) session.getAttribute("user");
		// 请求的IP
		// String ip = request.getRemoteAddr();

		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				// params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";
			}
		}
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			String operationType = "";
			String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						operationType = method.getAnnotation(Log.class).operationType();
						operationName = method.getAnnotation(Log.class).operationName();
						break;
					}
				}
			}
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:"
					+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")
					+ "." + operationType);
			System.out.println("方法描述:" + operationName);
			System.out.println("请求参数:" + params);
			// TODO 持久化到数据库
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==后置通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
		logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}",
				joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(),
				e.getMessage(), params);
	}

}
