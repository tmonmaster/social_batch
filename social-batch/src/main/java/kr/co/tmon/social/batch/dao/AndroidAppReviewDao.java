package kr.co.tmon.social.batch.dao;

import java.util.List;

import kr.co.tmon.social.batch.vo.AndroidAppReview;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 정승현 (RASPILLA16@tmon.co.kr)
 * 
 */
@Repository
public class AndroidAppReviewDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertAndroidAppReviewList(List<AndroidAppReview> googleReviewList) {
		return 0;
	}

	public int insertRelationList(List<AndroidAppReview> googleReviewList) {
		return 0;
	}
}
