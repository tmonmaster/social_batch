package kr.co.tmon.social.batch.dao;

import java.util.List;

import kr.co.tmon.social.batch.vo.GoogleReview;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 정승현 (RASPILLA16@tmon.co.kr)
 * 
 */
@Repository
public class GoogleReviewDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertGoogleReviewList(List<GoogleReview> googleReviewList) {
		return 0;
	}

	public int insertRelationList(List<GoogleReview> googleReviewList) {
		return 0;
	}
}
