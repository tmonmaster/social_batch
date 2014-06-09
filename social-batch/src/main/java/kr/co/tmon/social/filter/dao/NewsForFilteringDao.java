package kr.co.tmon.social.filter.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.co.tmon.social.filter.controller.FilteringController;
import kr.co.tmon.social.filter.vo.NewsForFiltering;

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

	public List<NewsForFiltering> getNewsForFilteringList(String date) {
		if (date == null)
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		else if (date.equals(FilteringController.FILTER_ALL))
			date = "0000-00-00";

		return sqlSession.selectList(NEWS_FOR_FILTERING + "getNewsForFilteringList", date);
	}

	public int updateRelationScoreList(List<NewsForFiltering> newsForFilteringList) {
		return sqlSession.update(NEWS_FOR_FILTERING + "updateRelationScoreList", newsForFilteringList);
	}
}
