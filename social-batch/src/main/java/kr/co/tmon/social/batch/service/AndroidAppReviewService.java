package kr.co.tmon.social.batch.service;

import java.util.List;

import kr.co.tmon.social.batch.dao.AndroidAppDao;
import kr.co.tmon.social.batch.dao.AndroidAppReviewDao;
import kr.co.tmon.social.batch.vo.AndroidApp;
import kr.co.tmon.social.batch.vo.AndroidAppReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 정승현 (RASPILLA16@tmon.co.kr)
 * 
 */
@Service
public class AndroidAppReviewService {

	@Autowired
	private AndroidAppDao androidAppDao;

	@Autowired
	private AndroidAppReviewDao androidAppReviewDao;

	public int insertReviewList() {
		List<AndroidApp> androidAppList = androidAppDao.getAppList();

		List<AndroidAppReview> androidAppReviewList = getAndroidAppReviewList(androidAppList);

		int insertedAndroidAppReview = androidAppReviewDao.insertAndroidAppReviewList(androidAppReviewList);
		androidAppReviewDao.insertRelationList(androidAppReviewList);

		return insertedAndroidAppReview;
	}

	private List<AndroidAppReview> getAndroidAppReviewList(List<AndroidApp> appList) {
		// TODO 구글 플레이 리뷰를 파싱하여 객체화
		return null;
	}
}
