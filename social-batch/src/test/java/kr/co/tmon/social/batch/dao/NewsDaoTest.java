package kr.co.tmon.social.batch.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.vo.News;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
public class NewsDaoTest {

	@Autowired
	private NewsDao newsDao;
	private List<News> newsList;

	@Before
	public void init() {
		newsList = new ArrayList<News>();
		for (int counter = 0; counter < 10; counter++) {
			News news = new News();
			news.setDate("2014-03-28 01:02:00");
			news.setImage("http://imgnews.naver.net/image/thumb140/5255/2014/03/28/13980.jpg");
			news.setLink("http://www.readersnews.com/news/articleView.html?idxno=46557");
			news.setPreview("<![CDATA[▶노래하는 슈퍼스타 꼬마 돼지 컬(브리짓 민느 글, 수지 카스터먼 그림, 강이경 옮김)=주인공 컬은 기린 인형을 늘 품에 안고 다니는 귀여운 꼬마 돼지다. 컬은 음악 듣는 것을 너무 좋아하고 노래를 부르고 싶어한다. 이를...]]>");
			news.setProvider("독서신문");
			news.setSocialName("티켓몬스터");
			news.setTitle("[새로나온 책]");
			newsList.add(news);
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertNewsList() throws Exception {
		assertNotNull(newsDao.insertNewsList(newsList));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertRelationList() throws Exception {
		assertNotNull(newsDao.insertRelationList(newsList));
	}

}
