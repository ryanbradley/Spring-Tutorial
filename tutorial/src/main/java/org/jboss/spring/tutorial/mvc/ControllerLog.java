package org.jboss.spring.tutorial.mvc;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ControllerLog {

	@Pointcut("execution(* org.jboss.spring.tutorial.mvc.InventoryController.handleRequest(..))")
	public void request() {
	}
	
	@Pointcut("execution(* org.jboss.spring.tutorial.service.SimpleProductManager.increasePrice(int)) && args(percentage)")
	public void increase(int percentage) {
	}
    
    protected final Log logger = LogFactory.getLog(getClass());
  
    @Before("request()")
    public void hello() {
        String now = (new Date()).toString();
        
        logger.info("Returning hello view with " + now); 	
    }
    
    @Before("increase(percentage)")
    public void increaseIntro(int percentage) {
        logger.info("Increasing prices by " + percentage + "%.");
    }
    
    @AfterReturning("increase(percentage)")
    public void result(int percentage) {
        logger.info("returning from PriceIncreaseForm view to hello.htm");
    }
}
