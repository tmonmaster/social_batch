package kr.co.tmon.social.batch.service;

import java.util.List;

import kr.co.tmon.social.batch.dao.NewsDao;
import kr.co.tmon.social.batch.vo.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 정승현 (raspilla16@tmon.co.kr)
 * 
 */
@Service
public class NewsService {
	@Autowired
	private NewsDao newsDao;

	public int insertNewsList(List<News> newsList) {
		int insertedNews = newsDao.insertNewsList(newsList);
		newsDao.insertRelationList(newsList);
		
		return insertedNews;
	}
}
