package kr.co.tmon.social.batch.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.service.AndroidAppReviewService;
import kr.co.tmon.social.batch.service.NaverNewsService;
import kr.co.tmon.social.batch.service.NewsService;
import kr.co.tmon.social.batch.vo.News;
import kr.co.tmon.social.filter.constant.FilteringConstant;
import kr.co.tmon.social.filter.service.NewsFilteringService;
import kr.co.tmon.social.filter.service.SingleNewsFilteringService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BatchControllerTest {

	@Mock
	private NaverNewsService naverNewsService;
	@Mock
	private NewsService newsService;
	@Mock
	private AndroidAppReviewService androidAppReviewService;
	@Mock
	private NewsFilteringService filteringService;
	@Mock
	private SingleNewsFilteringService singleNewsFilteringService;

	@InjectMocks
	private BatchController batchController;

	@Before
	public void init() throws Exception {
		List<News> newsList = new ArrayList<News>();
		when(naverNewsService.getNewsList()).thenReturn(newsList);
		when(newsService.insertNewsList(newsList)).thenReturn(0);
		when(filteringService.startNewsFiltering(FilteringConstant.FILTER_ALL)).thenReturn(0);
		when(singleNewsFilteringService.startSingleNewsFiltering(FilteringConstant.FILTER_ALL)).thenReturn(0);
	}

	@Test
	public void testDoNewsBatch() throws Exception {
		batchController.doNewsBatch();
	}

}
