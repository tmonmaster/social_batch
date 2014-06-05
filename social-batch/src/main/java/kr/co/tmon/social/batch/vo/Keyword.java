package kr.co.tmon.social.batch.vo;

/**
 * 
 * @author 강이경
 * 
 *         DB에서 추출한 데이터를 담을 클래스
 * 
 */
public class Keyword {
	public static final int CORE_KEYWORD = 0;

	private String companyId;
	private String companyKeyword;
	private int keywordPriority;

	public Keyword() {}

	public Keyword(String companyId, String companyKeyword, int keywordPriority) {
		this.companyId = companyId;
		this.companyKeyword = companyKeyword;
		this.keywordPriority = keywordPriority;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyKeyword() {
		return companyKeyword;
	}

	public void setCompanyKeyword(String companyKeyword) {
		this.companyKeyword = companyKeyword;
	}

	public int getKeywordPriority() {
		return keywordPriority;
	}

	public void setKeywordPriority(int keywordPriority) {
		this.keywordPriority = keywordPriority;
	}

	@Override
	public String toString() {
		return "Keyword [companyId=" + companyId + ", companyKeyword=" + companyKeyword + ", keywordPriority=" + keywordPriority + "]";
	}
}
