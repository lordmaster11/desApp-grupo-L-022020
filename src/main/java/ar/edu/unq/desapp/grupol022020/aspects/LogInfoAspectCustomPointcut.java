package ar.edu.unq.desapp.grupol022020.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(0)
public class LogInfoAspectCustomPointcut  {

	public static Logger logger = LoggerFactory.getLogger(LogInfoAspectCustomPointcut.class);
	
	/// CUSTOM  POINTCUT////
	@Pointcut("execution(* ar.edu.unq.desapp.grupol022020.webservices..*(..))")

	public void methodsStarterServicePointcut() {
	}

	@Before("methodsStarterServicePointcut()")
	public void beforeMethods() throws Throwable {
		logger.info("/////// BEFORE /////");
	}

	@After("methodsStarterServicePointcut()")
	public void afterMethods() throws Throwable {
		logger.info("/////// AFTER  /////");
	}
}