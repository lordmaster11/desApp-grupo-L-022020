package ar.edu.unq.desapp.grupol022020.aspects;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeAspectAnnotation {

	static Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspectAnnotation.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("/////// AROUND START  logExecutionTime annotation //////");

    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    	Method method = signature.getMethod();
	    List<Parameter> parameters = Arrays.asList(method.getParameters());
	    List<Object> arguments = Arrays.asList(joinPoint.getArgs());
	    getArguments(signature, parameters, arguments);

        getTime(signature, "STARTED");
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        getTime(signature, "FINISHED");
        logger.info("METHOD: " + signature.getName() + " in CLASS: " + signature.getDeclaringType().getSimpleName() + " EXECUTED in: " + executionTime + " ms");
        logger.info("/////// AROUND FINISH  logExecutionTime annotation ///////");
    
        return proceed;
    }	
	
	private void getTime(MethodSignature signature, String operation) {
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		int millis = now.get(ChronoField.MILLI_OF_SECOND);
		logger.info("METHOD: " + signature.getName() + " in CLASS: " + signature.getDeclaringType().getSimpleName() + 
				" " + operation + " at: " +hour + ":" + minute + ":" + second + ":" + millis + " on " + day + "-" + month + "-" + year);
	    }

	private void getArguments(MethodSignature signature, List<Parameter> parameters, List<Object> arguments) {
		logger.info("METHOD: " + signature.getName() + " in CLASS: " + signature.getDeclaringType().getSimpleName() + " CALLED WITH ARGUMENTS:");
        Iterator<Parameter> it1 = parameters.iterator();
        Iterator<Object> it2 = arguments.iterator();
        while(it1.hasNext() && it2.hasNext()) {
            Parameter parameter = (Parameter) it1.next();
            Object argument = it2.next();
            logger.info("TYPE: " + parameter.getType().getSimpleName() + " | NAME: " + parameter.getName() + " | VALUE:  " + argument.toString());
        }
    }
}