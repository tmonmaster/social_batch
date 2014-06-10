package kr.co.tmon.social.batch.controller;

import java.util.List;

import kr.co.tmon.social.batch.service.AndroidAppReviewService;
import kr.co.tmon.social.batch.service.NaverNewsService;
import kr.co.tmon.social.batch.service.NewsService;
import kr.co.tmon.social.batch.vo.News;
import kr.co.tmon.social.filter.service.FilteringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author raspilla16@tmon.co.kr
 * 
 */
@Controller
public class BatchController {
	@Autowired
	private NaverNewsService naverNewsService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private AndroidAppReviewService androidAppReviewService;
	@Autowired
	private FilteringService filteringService;

	public void doNewsBatch() throws Exception {
		List<News> newsList = naverNewsService.getNewsList();
		newsService.insertNewsList(newsList);
		filteringService.startNewsFiltering(FilteringService.FILTER_ALL);
	}

	public void doAndroidReviewBatch() throws Exception {
		androidAppReviewService.insertReviewList();
	}
}
