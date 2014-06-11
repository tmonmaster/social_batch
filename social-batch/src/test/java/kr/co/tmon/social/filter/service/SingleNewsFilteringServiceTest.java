package kr.co.tmon.social.filter.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.filter.dao.NewsForFilteringDao;
import kr.co.tmon.social.filter.vo.NewsForFiltering;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 단독 기사일 경우, 연관성에 가점을 준다.
 * 
 * @author 강이경
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SingleNewsFilteringServiceTest {

	@Mock
	private NewsForFilteringDao newsForFilteringDao;

	@InjectMocks
	private SingleNewsFilteringService singleNewsFilteringService;

	@Test
	public void RelationScore가_5점_가점_됬는지_확인() throws Exception {
		List<NewsForFiltering> dummySingleNewsList = new ArrayList<NewsForFiltering>();
		NewsForFiltering dummySingleNews = new NewsForFiltering();
		dummySingleNews.setCompanyId("12");
		dummySingleNews.setKeywordList("groupon,ticket monster,ticketmonster,tmon,그루폰,신현성,티몬,티켓 몬스터,티켓몬스터");
		dummySingleNews.setNewsId("30281");
		dummySingleNews.setPreview("특히 가장 선호하는 온라인 쇼핑몰 순위에는...");
		dummySingleNews.setRelationScore(0);
		dummySingleNews.setTitle("1929 소비자 명동 가로수길 즐겨 찾는다.");
		dummySingleNewsList.add(dummySingleNews);
		dummySingleNewsList.get(0).getRelationScore();

		when(newsForFilteringDao.getSingleNewsForFilteringList(anyString())).thenReturn(dummySingleNewsList);

		List<NewsForFiltering> actualSingleNewsList = singleNewsFilteringService.getSingleNewsListBy(anyString());
		singleNewsFilteringService.applyAdditionalRelationScore(actualSingleNewsList);

		assertEquals(dummySingleNewsList.size(), actualSingleNewsList.size());

		for (int index = 0; index < actualSingleNewsList.size(); index++) {
			assertEquals(SingleNewsFilteringService.ADDITIONAL_RELATION_SCORE, actualSingleNewsList.get(index).getRelationScore());
		}
	}
}
