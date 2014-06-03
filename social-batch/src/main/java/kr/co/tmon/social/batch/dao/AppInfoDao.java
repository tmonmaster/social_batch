package kr.co.tmon.social.batch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.tmon.social.batch.vo.AppInfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 *         DB에서 앱 정보(앱 버전, 아이디)를 가져온다.
 *         현재 구글 플레이에 등록 된 최신 앱 정보를 DB에 넣는다.
 *         구글 플레이에서 앱의 평균 별점 정보를 가져온다.
 *         
 */
@Repository
public class AppInfoDao {
	private final static String ANDROID_APP_REVIEW_MAPPER = "kr.co.tmon.social.batch.dao.mapper.AppInfoMapper.";

	@Autowired
	private SqlSession sqlSession;

	public List<AppInfo> getAppInfoList() {
		return sqlSession.selectList(ANDROID_APP_REVIEW_MAPPER + "getAppInfoList");
	}

	public void insertVersion(AppInfo appInfoFromGoogle) {
		sqlSession.insert(ANDROID_APP_REVIEW_MAPPER + "insertAppInfoFromGoogle", appInfoFromGoogle);
	}

	public void updateAverageScore(String appId, String averageScore) {
		Map<String, String> appAndScore = new HashMap<String, String>();
		appAndScore.put("appId", appId);
		appAndScore.put("averageScore", averageScore);

		sqlSession.update(ANDROID_APP_REVIEW_MAPPER + "updateAverageScore", appAndScore);
	}
}
