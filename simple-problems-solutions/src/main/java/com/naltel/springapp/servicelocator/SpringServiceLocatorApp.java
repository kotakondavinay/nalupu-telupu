package com.naltel.springapp.servicelocator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * Service Locator Design Pattern.
 * Add spring-beans,  spring-core, spring-context, 
 *  spring-context-support , spring-expression and commons.logging jar files.
 * @author Vinay Kumar.
 *
 */
public class SpringServiceLocatorApp {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
	      ApplicationContext context = 
	            new ClassPathXmlApplicationContext("com/naltel/springapp/servicelocator/SpringServiceLocatorBeans.xml");
	    IReadWriteServiceManager readWriteManager = (IReadWriteServiceManager) context.getBean("serviceManager");
		IReadWriteService readWriteService = readWriteManager.getIReadWriteService("STDIO");
	    readWriteService.read();
	    readWriteService.process();
	    readWriteService.write();
	    IReadWriteService readWriteService2 = readWriteManager.getIReadWriteService("FILE");
	    readWriteService2.read();
	    readWriteService2.process();
	    readWriteService2.write();
	}
}
