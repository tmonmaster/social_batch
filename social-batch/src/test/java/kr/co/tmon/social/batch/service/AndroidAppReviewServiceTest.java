package kr.co.tmon.social.batch.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AndroidAppReviewServiceTest {

	@Ignore
	@Test
	public void 안드로이드앱리뷰서비스테스트() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		AndroidAppReviewService androidAppReviewService = applicationContext.getBean("androidAppReviewService", AndroidAppReviewService.class);

		androidAppReviewService.insertReviewList();
	}
}
