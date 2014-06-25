package kr.co.tmon.social.batch.service;

import static org.junit.Assert.assertTrue;

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
public class AndroidRankingServiceTest {

	@Autowired
	private AndroidRankingService androidRankingService;

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertRanking() throws Exception {
		int insertedRow = androidRankingService.insertRanking();
		assertTrue(insertedRow != 0);
	}

}
