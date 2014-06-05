package kr.co.tmon.social.batch.filter.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.co.tmon.social.batch.filter.vo.NewsForFiltering;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class NewsForFilteringDaoTest {

	@Autowired
	private NewsForFilteringDao newsForFilteringDao;

	@Test
	public void testGetNewsForFilteringList() throws Exception {
		List<NewsForFiltering> newsForFilteringList = newsForFilteringDao.getNewsForFilteringList();

		System.out.println(newsForFilteringList);

		assertNotNull(newsForFilteringList);
	}

}
