package kr.co.tmon.social.batch.vo;

/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 */
public class AppInfo {
	private String appId;
	private String androidAppVersion;
	private int googleAppVersion;
	private int ranking;

	public AppInfo() {} // 마이바티스에서 사용하려면 있어야함

	public AppInfo(String appId, int googleAppVersion) {
		this.appId = appId;
		this.googleAppVersion = googleAppVersion;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAndroidAppVersion() {
		return androidAppVersion;
	}

	public void setAndroidAppVersion(String androidAppVersion) {
		this.androidAppVersion = androidAppVersion;
	}

	public int getGoogleAppVersion() {
		return googleAppVersion;
	}

	public void setGoogleAppVersion(int googleAppVersion) {
		this.googleAppVersion = googleAppVersion;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "AppInfo [appId=" + appId + ", androidAppVersion=" + androidAppVersion + ", googleAppVersion=" + googleAppVersion + ", ranking=" + ranking + "]";
	}
}
