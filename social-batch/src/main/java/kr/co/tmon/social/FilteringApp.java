package kr.co.tmon.social;

import kr.co.tmon.social.filter.constant.FilteringConstant;
import kr.co.tmon.social.filter.controller.NewsFilteringController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
public class FilteringApp {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		NewsFilteringController filteringController = applicationContext.getBean("newsFilteringController", NewsFilteringController.class);

		filteringController.applyFilter(FilteringConstant.FILTER_ALL);
	}
}
