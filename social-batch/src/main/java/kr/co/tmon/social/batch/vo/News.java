package kr.co.tmon.social.batch.vo;

/**
 * @author Yunho Lee
 * 
 */
public class News {
	private String companyId;
	private String title;
	private String preview;
	private String image;
	private String date;
	private String link;
	private String provider;

	public News() {}

	public News(String companyId, String title, String preview, String image, String date, String link, String provider) {
		super();
		this.companyId = companyId;
		this.title = title;
		this.preview = preview;
		this.image = image;
		this.date = date;
		this.link = link;
		this.provider = provider;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "News [companyId=" + companyId + ", title=" + title + ", preview=" + preview + ", image=" + image + ", date=" + date + ", link=" + link + ", provider=" + provider + "]";
	}
}
