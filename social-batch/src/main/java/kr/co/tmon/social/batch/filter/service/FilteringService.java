package kr.co.tmon.social.batch.filter.service;

import java.util.List;

import kr.co.tmon.social.batch.filter.dao.NewsForFilteringDao;
import kr.co.tmon.social.batch.filter.vo.NewsForFiltering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 * word count를 통해 뉴스와 회사간의 연관성을 구한다.
 * 
 */
@Service
public class FilteringService {
	private static final int WEIGHT_FOR_TITLE = 2;
	private static final int WEIGHT_FOR_PREVIEW = 1;
	private static final int NO_OCCURRENCE = -1;

	@Autowired
	private NewsForFilteringDao newsForFilteringDao;

	public List<NewsForFiltering> startNewsFiltering() throws Exception {
		List<NewsForFiltering> filteredNewsList = newsForFilteringDao.getNewsForFilteringList();

		int titleRelationScore;
		int previewRelationScore;
		int totalRelationScore = 0;

		for (int indexI = 0; indexI < filteredNewsList.size(); indexI++) {
			titleRelationScore = wordCount(filteredNewsList.get(indexI).getTitle(), filteredNewsList.get(indexI).getKeywordList()) * WEIGHT_FOR_TITLE;
			previewRelationScore = wordCount(filteredNewsList.get(indexI).getPreview(), filteredNewsList.get(indexI).getKeywordList()) * WEIGHT_FOR_PREVIEW;
			totalRelationScore = titleRelationScore + previewRelationScore;
			filteredNewsList.get(indexI).setRelationScore(totalRelationScore);
		}

		return filteredNewsList;
	}

	private int wordCount(String text, List<String> keyword) {
		int indexOf;
		int wordCount = 0;
		int fromIndex = 0;

		for (int keywordIndex = 0; keywordIndex < keyword.size(); keywordIndex++) {
			while (true) {
				indexOf = text.indexOf(keyword.get(keywordIndex), fromIndex);

				if (indexOf == NO_OCCURRENCE)
					break;

				fromIndex = indexOf + keyword.get(keywordIndex).length();
				wordCount++;
			}

			fromIndex = 0;
		}

		return wordCount;
	}
}
