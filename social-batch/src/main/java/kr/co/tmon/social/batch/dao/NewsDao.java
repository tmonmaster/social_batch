package kr.co.tmon.social.batch.dao;

import java.util.List;

import kr.co.tmon.social.batch.vo.News;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import twitter4j.Logger;

/**
 * @author 정승현 (raspilla16@tmon.co.kr)
 * 
 */
@Repository
public class NewsDao {

	private static final String NEWS_MAPPER = "kr.co.tmon.social.batch.dao.mapper.NewsMapper.";
	@Autowired
	private SqlSession sqlSession;

	public int insertNewsList(List<News> newsList) {
		return sqlSession.insert(NEWS_MAPPER + "insertNewsList", newsList);
	}

	public int insertRelationList(List<News> newsList) {
		for (News news : newsList) {
			Logger.getLogger(this.getClass()).info(news.getCompanyId() + " // " + news.getDate() + " // " + news.getPreview());
		}

		return sqlSession.insert(NEWS_MAPPER + "insertRelationList", newsList);
	}
}
