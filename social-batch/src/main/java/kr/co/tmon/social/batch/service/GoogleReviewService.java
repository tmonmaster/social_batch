package kr.co.tmon.social.batch.service;

import java.util.List;

import kr.co.tmon.social.batch.dao.AppDao;
import kr.co.tmon.social.batch.dao.GoogleReviewDao;
import kr.co.tmon.social.batch.vo.App;
import kr.co.tmon.social.batch.vo.GoogleReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 정승현 (RASPILLA16@tmon.co.kr)
 * 
 */
@Service
public class GoogleReviewService {

	@Autowired
	private AppDao appDao;

	@Autowired
	private GoogleReviewDao googleReviewDao;

	public int insertReviewList() {
		List<App> appList = appDao.getAppList();

		List<GoogleReview> googleReviewList = getGoogleReviewList(appList);

		int insertedGoogleReview = googleReviewDao.insertGoogleReviewList(googleReviewList);
		googleReviewDao.insertRelationList(googleReviewList);

		return insertedGoogleReview;
	}

	private List<GoogleReview> getGoogleReviewList(List<App> appList) {
		// TODO 구글 플레이 리뷰를 파싱하여 객체화
		return null;
	}
}
