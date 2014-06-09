package kr.co.tmon.social.filter.controller;

import kr.co.tmon.social.filter.service.FilteringService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
public class FilteringController {
	public static final String FILTER_ALL = "all";

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

		FilteringService filteringService = applicationContext.getBean("filteringService", FilteringService.class);

		filteringService.startNewsFiltering(FILTER_ALL);
	}
}
