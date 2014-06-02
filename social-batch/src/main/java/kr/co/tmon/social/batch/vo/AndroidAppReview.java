package kr.co.tmon.social.batch.vo;

/**
 * @author raspilla16@tmon.co.kr
 * 
 */
public class AndroidAppReview {
	private String reviewLink;
	private String userName;
	private String userLink;
	private String reviewDate;
	private String starScore;
	private String reviewTitle;
	private String reviewContent;
	private int googleAppVersion;
	private String androidAppId;

	public String getReviewLink() {
		return reviewLink;
	}

	public void setReviewLink(String reviewLink) {
		this.reviewLink = reviewLink;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLink() {
		return userLink;
	}

	public void setUserLink(String userLink) {
		this.userLink = userLink;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getStarScore() {
		return starScore;
	}

	public void setStarScore(String starScore) {
		this.starScore = starScore;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getGoogleAppVersion() {
		return googleAppVersion;
	}

	public void setGoogleAppVersion(int googleAppVersion) {
		this.googleAppVersion = googleAppVersion;
	}

	public String getAndroidAppId() {
		return androidAppId;
	}

	public void setAndroidAppId(String androidAppId) {
		this.androidAppId = androidAppId;
	}

	@Override
	public String toString() {
		return "AndroidAppReview [reviewLink=" + reviewLink + ", userName=" + userName + ", userLink=" + userLink + ", reviewDate=" + reviewDate + ", starScore=" + starScore + ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", googleAppVersion=" + googleAppVersion + ", androidAppId=" + androidAppId + "]";
	}

}
