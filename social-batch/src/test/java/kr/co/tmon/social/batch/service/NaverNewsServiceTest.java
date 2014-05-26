package kr.co.tmon.social.batch.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.co.tmon.social.batch.vo.News;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Yunho Lee
 * 
 * 네이버 뉴스 RSS 파싱 테스트
 * 
 */

public class NaverNewsServiceTest {

	@Test
	public void parsingTest() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		NaverNewsService naverNewsService = applicationContext.getBean("naverNewsService", NaverNewsService.class);

		List<News> naverNewsList = naverNewsService.getNewsList();

		assertNotNull(naverNewsList);

		for (int i = 0; i < naverNewsList.size(); i++)
			System.out.println(naverNewsList.get(i).toString());

	}
}
