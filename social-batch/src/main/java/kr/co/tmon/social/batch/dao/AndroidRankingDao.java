package kr.co.tmon.social.batch.dao;

import java.util.List;

import kr.co.tmon.social.batch.vo.AppInfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
@Repository
public class AndroidRankingDao {
	private final static String ANDROID_APP_REVIEW_MAPPER = "kr.co.tmon.social.batch.dao.mapper.AppInfoMapper.";

	@Autowired
	private SqlSession sqlSession;

	public int insertRanking(List<AppInfo> appInfoList) {
		return sqlSession.insert(ANDROID_APP_REVIEW_MAPPER + "insertRankingFromGoogle", appInfoList);
	}
}
