package kr.co.tmon.social.batch.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.vo.AndroidAppReview;

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
public class AndroidAppReviewDaoTest {

	@Autowired
	private AndroidAppReviewDao androidAppReviewDao;

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertAndroidAppReviewList() throws Exception {
		List<AndroidAppReview> androidAppReviewList = new ArrayList<AndroidAppReview>();
		AndroidAppReview androidAppReview = new AndroidAppReview();
		androidAppReview.setReviewLink("http://test.com/reviewlink");
		androidAppReview.setUserName("lee");
		androidAppReview.setUserLink("http://test.com/userlink");
		androidAppReview.setReviewDate("2014-05-30");
		androidAppReview.setStarScore(80);
		androidAppReview.setReviewTitle("정말좋아요");
		androidAppReview.setReviewContent("정말로요");
		androidAppReview.setGoogleAppVersion(47);
		androidAppReview.setAndroidAppId("com.tmon");
		androidAppReviewList.add(androidAppReview);

		androidAppReviewDao.insertAndroidAppReviewList(androidAppReviewList);
	}

	@Test
		public void testGetLastReviewDateFromDbForTargetVersion() throws Exception {
			System.out.println(androidAppReviewDao.getLastReviewDateFromDbForTargetVersion("com.tmon", 47));
		}

	@Test
		public void testGetLatestVersionOfReview() throws Exception {
			System.out.println(androidAppReviewDao.getLatestVersionOfReview("com.tmon"));
		}

}
