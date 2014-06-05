package kr.co.tmon.social.batch.filter.vo;

import java.util.List;

/**
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 */
public class NewsForFiltering {
	private static final int WEIGHT_FOR_TITLE = 2;
	private static final int WEIGHT_FOR_PREVIEW = 1;
	
	private String newsId;
	private String companyId;
	private String title;
	private String preview;
	private List<String> keywordList;
	private int relationScore;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public List<String> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
	}

	public int getRelationScore() {
		return relationScore;
	}

	public void setRelationScore(int relationScore) {
		this.relationScore = relationScore;
	}

	@Override
	public String toString() {
		return "NewsForFiltering [newsId=" + newsId + ", companyId=" + companyId + ", title=" + title + ", preview=" + preview + ", keywordList=" + keywordList + ", relationScore=" + relationScore + "]";
	}

}
