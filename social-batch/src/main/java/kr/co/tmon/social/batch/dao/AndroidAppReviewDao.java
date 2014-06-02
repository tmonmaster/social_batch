package kr.co.tmon.social.batch.dao;

import java.util.Date;
import java.util.List;

import kr.co.tmon.social.batch.vo.AndroidAppReview;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
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

	public String getLastReviewDate() {
		return sqlSession.selectOne(ANDROID_APP_REVIEW_MAPPER + "getLastReviewDate");

	}
}
