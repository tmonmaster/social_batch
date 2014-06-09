package kr.co.tmon.social;

import kr.co.tmon.social.filter.controller.FilteringController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
public class FilteringApp {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		FilteringController filteringController = applicationContext.getBean("filteringController", FilteringController.class);
		
		filteringController.applyFilter(FilteringController.FILTER_ALL);
	}
}
