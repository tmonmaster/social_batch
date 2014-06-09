package kr.co.tmon.social.filter.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.filter.dao.NewsForFilteringDao;
import kr.co.tmon.social.filter.vo.NewsForFiltering;

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
		newsForFilteringTmon.setPreview("티켓몬스터 홈페이지에서 1봉지당 2550원에 판매 중이다. 삼성테크윈, 보안시스템 스페인 수출 삼성테크윈은 스페인의 가정용 보안 서비스업체인 시큐리타스 다이렉트와 영상감시카메라 20만대 및 보안 시스템 운용에...티켓몬스터");
		newsForFilteringTmon.setKeywordList("ticketmonster,tmon,신현성,티몬,티켓몬스터");
		newsForFilteringList.add(newsForFilteringTmon);

		newsForFilteringCoupang.setNewsId("30290");
		newsForFilteringCoupang.setCompanyId("13");
		newsForFilteringCoupang.setTitle("쿠팡 “에어부산으로 여름휴가 미리 준비하세요!”");
		newsForFilteringCoupang.setPreview("소셜커머스 쿠팡이 여름 휴가철을 미리 준비하는... 선봬= 쿠팡은 에어부산의 편도 및 왕복 항공권을... 쿠팡은 에어부산 항공권, 고급 리조트, 중형세단... 김춘희 쿠팡 국내여행 팀장은 “이번 에어부산 기획전은...");
		newsForFilteringCoupang.setKeywordList("coupang,김범석,쿠팡");
		newsForFilteringList.add(newsForFilteringCoupang);

		String date = "2014-06-09";
		when(newsForFilteringDao.getNewsForFilteringList(date)).thenReturn(newsForFilteringList);
		when(newsForFilteringDao.updateRelationScoreList(newsForFilteringList)).thenReturn(2);

		int insertedRow = filteringService.startNewsFiltering(date);
		System.out.println(newsForFilteringTmon.getKeywordList());
		System.out.println(wordCount(newsForFilteringTmon.getPreview(), newsForFilteringTmon.getKeywordList()));
		System.out.println(insertedRow);
		assertEquals(2, insertedRow);
	}

	private int wordCount(String text, List<String> keywordList) {
		int wordCount = 0;

		for (String keyword : keywordList) {
			int index = 0;

			while (true) {
				index = text.indexOf(keyword, index);

				if (index == -1)
					break;

				index += keyword.length();
				wordCount++;
			}
		}

		return wordCount;
	}
}
