package kr.co.tmon.social.batch.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.vo.AppInfo;

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
public class AndroidRankingDaoTest {

	@Autowired
	private AndroidRankingDao androidRankingDao;

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertRanking() throws Exception {
		List<AppInfo> appInfoList = new ArrayList<AppInfo>();
		AppInfo appInfo = new AppInfo();
		appInfo.setAndroidAppVersion("3.1.1");
		appInfo.setAppId("com.tmon");
		appInfo.setGoogleAppVersion(47);
		appInfo.setRanking(61);
		appInfoList.add(appInfo);

		int insertedRow = androidRankingDao.insertRanking(appInfoList);

		assertEquals(1, insertedRow);
	}
}
