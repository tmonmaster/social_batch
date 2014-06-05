package kr.co.tmon.social.batch.filter.dao;

import java.util.List;

import kr.co.tmon.social.batch.filter.vo.NewsForFiltering;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
@Repository
public class NewsForFilteringDao {

	private static final String NEWS_FOR_FILTERING = "kr.co.tmon.social.batch.filter.dao.mapper.NewsForFilteringMapper.";

	@Autowired
	private SqlSession sqlSession;

	public List<NewsForFiltering> getNewsForFilteringList() {
		return sqlSession.selectList(NEWS_FOR_FILTERING + "getNewsForFilteringList");
	}
}
