package kr.co.tmon.social.batch.dao;

import java.util.Date;
import java.util.List;

import kr.co.tmon.social.batch.vo.AndroidAppReview;
import kr.co.tmon.social.batch.vo.AppInfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 *         안드로이드 앱 리뷰를 DB에 넣는다. 앱 버전별 가장 최근 리뷰의 DATE를 가져온다. 가장 최근 리뷰의 앱 버전정보를 가져온다.
 * 
 */
@Repository
public class AndroidAppReviewDao {
	private final static String ANDROID_APP_REVIEW_MAPPER = "kr.co.tmon.social.batch.dao.mapper.AndroidAppReviewMapper.";

	@Autowired
	private SqlSession sqlSession;

	public int insertAndroidAppReviewList(List<AndroidAppReview> googleReviewList) {
		return sqlSession.insert(ANDROID_APP_REVIEW_MAPPER + "insertAndroidAppReviewList", googleReviewList);
	}

	public Date getLastReviewDateFromDbForTargetVersion(String appId, int targetVersion) {
		AppInfo appInfo = new AppInfo(appId, targetVersion);

		return sqlSession.selectOne(ANDROID_APP_REVIEW_MAPPER + "getLastReviewDate", appInfo);
	}

	public int getLatestVersionOfReview(String appId) {
		Integer lastReviewVersion = sqlSession.selectOne(ANDROID_APP_REVIEW_MAPPER + "getLatestVersionOfReview", appId);

		if (lastReviewVersion == null)
			return 1;

		return lastReviewVersion;
	}
}
