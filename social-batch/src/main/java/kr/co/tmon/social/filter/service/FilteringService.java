package kr.co.tmon.social.filter.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.tmon.social.filter.dao.NewsForFilteringDao;
import kr.co.tmon.social.filter.vo.NewsForFiltering;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 *         word count를 통해 뉴스와 회사간의 연관성을 구한다.
 * 
 */
@Service
public class FilteringService {
	private static final int DEFAULT_WEIGHT_FOR_TITLE = 5;
	private static final int DEFAULT_WEIGHT_FOR_PREVIEW = 1;
	private static final int END_OF_INDEX = -1;

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private NewsForFilteringDao newsForFilteringDao;

	public int startNewsFiltering(String date) throws Exception {
		List<NewsForFiltering> filteredNewsList = newsForFilteringDao.getNewsForFilteringList(date);
		List<NewsForFiltering> newsListToDelete = new ArrayList<NewsForFiltering>();

		int titleRelationScore = 0;
		int previewRelationScore = 0;
		int totalRelationScore = 0;

		for (NewsForFiltering newsForFiltering : filteredNewsList) {
			titleRelationScore = wordCount(newsForFiltering.getTitle(), newsForFiltering.getKeywordList()) * DEFAULT_WEIGHT_FOR_TITLE;
			previewRelationScore = wordCount(newsForFiltering.getPreview(), newsForFiltering.getKeywordList()) * DEFAULT_WEIGHT_FOR_PREVIEW;
			totalRelationScore = titleRelationScore + previewRelationScore;
			newsForFiltering.setRelationScore(totalRelationScore);

			if (totalRelationScore == 0)
				newsListToDelete.add(newsForFiltering);
			log.info(newsForFiltering);
		}

		filteredNewsList.removeAll(newsListToDelete);

		return newsForFilteringDao.updateRelationScoreList(filteredNewsList);
	}

	private int wordCount(String text, List<String> keywordList) {
		int wordCount = 0;

		for (String keyword : keywordList) {
			int index = 0;

			while (true) {
				index = text.indexOf(keyword, index);

				if (index == END_OF_INDEX)
					break;

				index += keyword.length();
				wordCount++;
			}
		}

		return wordCount;
	}
}
