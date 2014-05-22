package kr.co.tmon.social.batch.vo;

/**
 * 
 * @author 강이경
 * 
 *         DB에서 추출한 데이터를 담을 클래스
 * 
 */
public class Keyword {
	private String	companyName;
	private String	companyKeyword;

	public Keyword() {}

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
