package kr.co.tmon.social.batch.dao;

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
/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
public class AppInfoDaoTest {

	@Autowired
	private AppInfoDao appInfoDao;

	@Test
	public void testGetAppInfoList() throws Exception {
		List<AppInfo> appInfoList = appInfoDao.getAppInfoList();
		System.out.println(appInfoList);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertVersion() throws Exception {
		AppInfo appInfo = new AppInfo();
		appInfo.setAndroidAppVersion("3.1.1");
		appInfo.setAppId("com.tmon");
		appInfo.setGoogleAppVersion(48);
		appInfoDao.insertVersion(appInfo);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateAverageScore() throws Exception {
		appInfoDao.updateAverageScore("com.tmon", "4.9");
	}

}
