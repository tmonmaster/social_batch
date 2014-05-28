package kr.co.tmon.social.batch.vo;

/**
 * @author raspilla16@tmon.co.kr
 * 
 */
public class AndroidApp {
	private String androidAppId;
	private String companyId;

	public String getAndroidAppId() {
		return androidAppId;
	}

	public void setAndroidAppId(String androidAppId) {
		this.androidAppId = androidAppId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "AndroidApp [androidAppId=" + androidAppId + ", companyId=" + companyId + "]";
	}
}
