package kr.co.tmon.social.batch.filter.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.filter.dao.NewsForFilteringDao;
import kr.co.tmon.social.batch.filter.vo.NewsForFiltering;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FilteringServiceTest {

	@Mock
	private NewsForFilteringDao newsForFilteringDao;

	@InjectMocks
	private FilteringService filteringService;

	@Test
	public void 필터링테스트() throws Exception {
		List<NewsForFiltering> newsForFilteringList = new ArrayList<>();

		NewsForFiltering newsForFilteringTmon = new NewsForFiltering();
		NewsForFiltering newsForFilteringCoupang = new NewsForFiltering();
		
		newsForFilteringTmon.setNewsId("30281");
		newsForFilteringTmon.setCompanyId("12");
		newsForFilteringTmon.setTitle("1929 소비자 \"e-몰•명동•가로수길 즐겨 찾는다\"");
		newsForFilteringTmon.setPreview("특히 가장 선호하는 온라인 쇼핑몰 순위에는 지난해 순위에 없던 티몬과 쿠팡 등 소셜커머스가 상위권을... 쿠팡, 티몬, 위메프 등 소셜 커머스 족들도 크게 늘었다는 점이 흥미롭다. '온라인 쇼핑을 선호하는 이유'에...");
		newsForFilteringTmon.setKeywordList("ticketmonster,tmon,신현성,티몬,티켓몬스터");
		newsForFilteringList.add(newsForFilteringTmon);
		
		newsForFilteringCoupang.setNewsId("30290");
		newsForFilteringCoupang.setCompanyId("13");
		newsForFilteringCoupang.setTitle("쿠팡 “에어부산으로 여름휴가 미리 준비하세요!”");
		newsForFilteringCoupang.setPreview("소셜커머스 쿠팡이 여름 휴가철을 미리 준비하는... 선봬= 쿠팡은 에어부산의 편도 및 왕복 항공권을... 쿠팡은 에어부산 항공권, 고급 리조트, 중형세단... 김춘희 쿠팡 국내여행 팀장은 “이번 에어부산 기획전은...");
		newsForFilteringCoupang.setKeywordList("coupang,김범석,쿠팡");
		newsForFilteringList.add(newsForFilteringCoupang);
		
		when(newsForFilteringDao.getNewsForFilteringList()).thenReturn(newsForFilteringList);

		List<NewsForFiltering> filteredNewsList = filteringService.startNewsFiltering();
		
		for(NewsForFiltering newsForFiltering : filteredNewsList)
			System.out.println(newsForFiltering.toString());
	}
}
