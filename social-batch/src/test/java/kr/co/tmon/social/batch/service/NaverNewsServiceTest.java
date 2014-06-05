package kr.co.tmon.social.batch.service;

import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.dao.KeywordDao;
import kr.co.tmon.social.batch.vo.Keyword;
import kr.co.tmon.social.batch.vo.News;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author Yunho Lee
 * 
 *         네이버 뉴스 RSS 파싱 테스트
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class NaverNewsServiceTest {

	@Mock
	private KeywordDao keywordDao;

	@InjectMocks
	private NaverNewsService naverNewsService;

	@Test
	public void parsingTest() throws Exception {
		List<Keyword> keywordList = new ArrayList<>();
		keywordList.add(new Keyword("12", "티몬", 0));
		keywordList.add(new Keyword("13", "쿠팡", 0));
		keywordList.add(new Keyword("14", "위메프", 0));

		when(keywordDao.getKeywordList()).thenReturn(keywordList);

		List<News> naverNewsList = naverNewsService.getNewsList();

		assertNotSame(0, naverNewsList.size());

		for (int i = 0; i < naverNewsList.size(); i++)
			System.out.println(naverNewsList.get(i).toString());
	}
}
