package kr.co.tmon.social.batch.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.batch.vo.News;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Yunho Lee 네이버 뉴스 RSS 파싱 테스트
 * 
 */

public class NaverNewsServiceTest {
	NaverNewsService	domParsing;
	List<Keyword>		keywordList;

	@Before
	public void setup() {
		domParsing = new NaverNewsService();
		keywordList = new ArrayList<Keyword>();
		keywordList.add(new Keyword("티켓몬스터", "티몬"));
		keywordList.add(new Keyword("쿠팡", "쿠팡"));
		keywordList.add(new Keyword("위메프", "위메프"));
		keywordList.add(new Keyword("티켓몬스터", "티켓몬스터"));

	}

	@Test
	public void parsingTest() throws Exception {
		List<News> naverNewsList = domParsing.getNewsList(keywordList);

		assertNotNull(naverNewsList);

		for (int i = 0; i < naverNewsList.size(); i++)
			System.out.println(naverNewsList.get(i).toString());

	}
}

class Keyword {
	private String	companyName;
	private String	companyKeyword;

	public Keyword(String companyName, String companyKeyword) {
		this.companyName = companyName;
		this.companyKeyword = companyKeyword;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyKeyword() {
		return companyKeyword;
	}

	public void setCompanyKeyword(String companyKeyword) {
		this.companyKeyword = companyKeyword;
	}

	@Override
	public String toString() {
		return "Keyword [companyName=" + companyName + ", companyKeyword=" + companyKeyword + "]";
	}

}
