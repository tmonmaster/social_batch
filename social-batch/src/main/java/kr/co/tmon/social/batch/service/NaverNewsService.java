package kr.co.tmon.social.batch.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kr.co.tmon.social.batch.dao.KeywordDao;
import kr.co.tmon.social.batch.vo.Keyword;
import kr.co.tmon.social.batch.vo.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * @author Yunho Lee
 * 
 *         소셜커머스 3사 키워드로 검색한 네이버 뉴스 RSS를 파싱하여 List로 넘겨준다.
 * 
 */
@Service
public class NaverNewsService {

	private static final String	URL				= "http://newssearch.naver.com/search.naver?where=rss&query=";
	private static final String	ITEM			= "item";
	private static final String	TITLE			= "title";
	private static final String	LINK			= "link";
	private static final String	DESCRIPTION		= "description";
	private static final String	PUBDATE			= "pubDate";
	private static final String	AUTHOR			= "author";
	private static final String	THUMBNAIL		= "media:thumbnail";

	@Autowired
	private KeywordDao keywordDao;
	
	List<News>					naverNewsList	= new ArrayList<News>();

	public List<News> getNewsList() throws Exception {
		List<Keyword> keywordList = keywordDao.getKeywordList();
		
		for (int index = 0; index < keywordList.size(); index++)
			parse(keywordList.get(index).getCompanyName(), keywordList.get(index).getCompanyKeyword());

		return naverNewsList;
	}

	private void parse(String companyName, String companyKeyword) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		String rssPath = URL + URLEncoder.encode(companyKeyword, "UTF-8");
		Document document = documentBuilder.parse(rssPath);
		getTagValueAndAttribute(document, companyName);
	}

	private List<News> getTagValueAndAttribute(Document document, String companyName) {
		Element tagValueRoot = document.getDocumentElement();
		NodeList tagValueNodeList = tagValueRoot.getElementsByTagName(ITEM);

		Element attributeRoot = document.getDocumentElement();
		NodeList attributeNodeList = attributeRoot.getElementsByTagName(THUMBNAIL);

		for (int index = 0; index < tagValueNodeList.getLength(); index++) {
			Element element = (Element) tagValueNodeList.item(index);
			NamedNodeMap attributeMap = attributeNodeList.item(index).getAttributes();

			naverNewsList.add(new News(companyName, getItem(element, TITLE), getItem(element, DESCRIPTION), attributeMap.item(0).getNodeValue(), getItem(element, PUBDATE), getItem(element, LINK), getItem(element, AUTHOR)));
		}

		return naverNewsList;
	}

	private static String getItem(Element inputElement, String tagName) {
		NodeList nodeList = inputElement.getElementsByTagName(tagName);
		Element element = (Element) nodeList.item(0);

		if (element.getFirstChild() != null)
			return element.getFirstChild().getNodeValue();
		else
			return "";
	}
}
