package kr.co.tmon.social.batch.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.vo.News;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NewsServiceTest {
	private NewsService newsService;
	private List<News> newsList;

	@Before
	public void init() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		newsService = applicationContext.getBean("newsService", NewsService.class);
		newsList = new ArrayList<News>();
		for (int counter = 0; counter < 10; counter++) {
			News news = new News();
			news.setDate("2014-03-28 01:02:00");
			news.setImage("http://imgnews.naver.net/image/thumb140/5255/2014/03/28/13980.jpg");
			news.setLink("http://www.readersnews.com/news/articleView.html?idxno=46557");
			news.setPreview("<![CDATA[▶노래하는 슈퍼스타 꼬마 돼지 컬(브리짓 민느 글, 수지 카스터먼 그림, 강이경 옮김)=주인공 컬은 기린 인형을 늘 품에 안고 다니는 귀여운 꼬마 돼지다. 컬은 음악 듣는 것을 너무 좋아하고 노래를 부르고 싶어한다. 이를...]]>");
			news.setProvider("독서신문");
			news.setCompanyId("12");
			news.setTitle("[새로나온 책]");
			newsList.add(news);
		}
	}

	@Test
	public void testInsertNewsList() throws Exception {
		assertNotNull(newsService.insertNewsList(newsList));
	}

}
