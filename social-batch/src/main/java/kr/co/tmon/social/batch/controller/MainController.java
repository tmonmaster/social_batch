package kr.co.tmon.social.batch.controller;

import java.util.List;

import kr.co.tmon.social.batch.service.GoogleReviewService;
import kr.co.tmon.social.batch.service.NaverNewsService;
import kr.co.tmon.social.batch.service.NewsService;
import kr.co.tmon.social.batch.vo.GoogleReview;
import kr.co.tmon.social.batch.vo.News;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author raspilla16@tmon.co.kr
 * 
 */
public class MainController {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");

		NaverNewsService naverNewsService = applicationContext.getBean("naverNewsService", NaverNewsService.class);
		List<News> newsList = naverNewsService.getNewsList();

		NewsService newsService = applicationContext.getBean("newsService", NewsService.class);
		newsService.insertNewsList(newsList);

		GoogleReviewService googleReviewService = applicationContext.getBean("googleReviewService", GoogleReviewService.class);
		googleReviewService.insertReviewList();
	}
}
