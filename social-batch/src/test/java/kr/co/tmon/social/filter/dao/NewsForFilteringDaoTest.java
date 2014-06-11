package kr.co.tmon.social.filter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.co.tmon.social.filter.vo.NewsForFiltering;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
public class NewsForFilteringDaoTest {

	@Autowired
	private NewsForFilteringDao newsForFilteringDao;

	@Test
	public void testGetNewsForFilteringList() throws Exception {
		List<NewsForFiltering> newsForFilteringList = newsForFilteringDao.getNewsForFilteringList("2114-06-20");

		System.out.println(newsForFilteringList);

		assertNotNull(newsForFilteringList);
		assertEquals(0, newsForFilteringList.size());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateRelationScoreList() throws Exception {
		List<NewsForFiltering> newsForFilteringList = newsForFilteringDao.getNewsForFilteringList(null);

		int updatedRows = newsForFilteringDao.updateRelationScoreList(newsForFilteringList);
		System.out.println(updatedRows);
	}

	@Test
	public void getSingleNewsForFilteringList() throws Exception {
		List<NewsForFiltering> singleNewsForFilteringList = newsForFilteringDao.getSingleNewsForFilteringList("2014-06-10");

		System.out.println(singleNewsForFilteringList);

		assertNotNull(singleNewsForFilteringList);
		assertEquals(50, singleNewsForFilteringList.size());
	}
}
