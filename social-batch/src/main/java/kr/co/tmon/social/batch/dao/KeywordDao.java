package kr.co.tmon.social.batch.dao;

import java.util.List;

import kr.co.tmon.social.batch.vo.Keyword;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author 강이경
 * 
 * DB의 정보를 Keyword 객체에 가져오는 클래스
 *
 */
@Repository
public class KeywordDao {
	@Autowired
	private SqlSession	sqlSession;

	/**
	 *
	 * 커밋 테스트
	 * @return
	 */
	public List<Keyword> getKeywordList() {
		return sqlSession.selectList("kr.co.tmon.social.batch.vo.Keyword.getKeyword");
	}
}
