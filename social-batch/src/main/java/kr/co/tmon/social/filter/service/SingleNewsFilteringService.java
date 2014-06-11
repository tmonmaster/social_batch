package kr.co.tmon.social.filter.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.filter.dao.NewsForFilteringDao;
import kr.co.tmon.social.filter.vo.NewsForFiltering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 단독 기사일 경우, 연관성에 가점을 준다.
 * 
 * @author 강이경
 * 
 */

@Service
public class SingleNewsFilteringService {
	public final static int ADDITIONAL_RELATION_SCORE = 5;

	@Autowired
	private NewsForFilteringDao newsForFilteringDao;

	public int startSingleNewsFiltering(String date) throws Exception {
		List<NewsForFiltering> singleNewsList = new ArrayList<NewsForFiltering>();
		singleNewsList = newsForFilteringDao.getSingleNewsForFilteringList(date);

		return applyAdditionalRelationScore(singleNewsList);
	}

	private int applyAdditionalRelationScore(List<NewsForFiltering> singleNewsList) {
		for (NewsForFiltering singleNews : singleNewsList)
			singleNews.setRelationScore(singleNews.getRelationScore() + ADDITIONAL_RELATION_SCORE);

		return newsForFilteringDao.updateRelationScoreList(singleNewsList);
	}
}
