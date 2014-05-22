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
	private static final String	KEYWORD_MAPPER	= "kr.co.tmon.social.batch.dao.mapper.Keyword.";
	@Autowired
	private SqlSession sqlSession;

	public List<Keyword> getKeywordList() {
		return sqlSession.selectList(KEYWORD_MAPPER + "getKeyword");
	}
}
