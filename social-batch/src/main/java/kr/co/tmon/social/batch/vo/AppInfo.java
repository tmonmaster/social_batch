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

	@Override
	public String toString() {
		return "AppInfo [appId=" + appId + ", androidAppVersion=" + androidAppVersion + ", googleAppVersion=" + googleAppVersion + "]";
	}

}
