package kr.co.tmon.social.batch.controller;

import java.util.List;

import kr.co.tmon.social.batch.service.AndroidAppReviewService;
import kr.co.tmon.social.batch.service.AndroidRankingService;
import kr.co.tmon.social.batch.service.NaverNewsService;
import kr.co.tmon.social.batch.service.NewsService;
import kr.co.tmon.social.batch.vo.News;
import kr.co.tmon.social.filter.constant.FilteringConstant;
import kr.co.tmon.social.filter.service.NewsFilteringService;
import kr.co.tmon.social.filter.service.SingleNewsFilteringService;

import org.apache.log4j.Logger;
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
	private AndroidRankingService androidRankingService;
	@Autowired
	private NewsFilteringService newsFilteringService;
	@Autowired
	private SingleNewsFilteringService singleNewsFilteringService;

	private Logger logger = Logger.getLogger(BatchController.class);

	public void doNewsBatch() throws Exception {
		List<News> newsList = naverNewsService.getNewsList();

		int insertedNewsCount = newsService.insertNewsList(newsList);
		logger.info("수집한 뉴스 : " + insertedNewsCount);

		newsFilteringService.startNewsFiltering(FilteringConstant.FILTER_ALL);
		singleNewsFilteringService.startSingleNewsFiltering(FilteringConstant.FILTER_ALL);
	}

	public void doAndroidReviewBatch() throws Exception {
		androidAppReviewService.insertReviewList();
	}

	public void doAndroidRankingBatch() throws Exception {
		androidRankingService.insertRanking();
	}
}
