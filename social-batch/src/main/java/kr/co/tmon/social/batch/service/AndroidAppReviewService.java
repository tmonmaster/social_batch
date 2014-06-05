package kr.co.tmon.social.batch.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

import kr.co.tmon.social.batch.dao.AndroidAppReviewDao;
import kr.co.tmon.social.batch.dao.AppInfoDao;
import kr.co.tmon.social.batch.vo.AndroidAppReview;
import kr.co.tmon.social.batch.vo.AppInfo;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.JSONArray;
import twitter4j.JSONException;

/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 */
@Service
public class AndroidAppReviewService {

	private static final int INDEX_OF_JSON_BODY = 2;
	private static final int INDEX_OF_JSON_STATUS = 1;

	private static final int GOOGLE_JSON_STATUS_LAST_PAGE = 2;
	private static final int GOOGLE_JSON_STATUS_PAGE_NOT_FOUND = 3;

	private static final int DEFAULT_VERSION_COVERAGE = 5;
	private static final int DEFAULT_DATE_COVERAGE = 3;
	private static final int END_OF_PAGE = 225;
	private static final int TIME_MILLIS_FOR_A_DAY = 1000 * 60 * 60 * 24;

	private static final String GOOGLE_PLAY = "https://play.google.com";
	private static final String GOOGLE_PLAY_REQUEST_URL = "https://play.google.com/store/apps/details?id=";
	private static final String REVIEW_REQUEST_URL = "https://play.google.com/store/getreviews";

	private static final String COOKIE_GOOGLE_ABUSE_EXEMPTION = "GOOGLE_ABUSE_EXEMPTION=ID=17802835f9ab3ed9:TM=1401966728:C=c:IP=114.201.245.130-:S=APGng0uPwwJh7x-orN8osg3rrYzqXz4JmA";

	@Autowired
	private AppInfoDao appInfoDao;

	@Autowired
	private AndroidAppReviewDao androidAppReviewDao;

	private Logger log = Logger.getLogger(this.getClass());

	private List<AppInfo> appInfoList;

	public void insertReviewList() throws Exception {
		appInfoList = appInfoDao.getAppInfoList();

		for (AppInfo appInfo : appInfoList) {
			checkLatestVersion(appInfo);
			insertReviewListForTargetApp(appInfo);
		}
	}

	private void insertReviewListForTargetApp(AppInfo appInfo) throws Exception {
		int latestVersionOfReview = androidAppReviewDao.getLatestVersionOfReview(appInfo.getAppId());

		for (int targetVersion = latestVersionOfReview - DEFAULT_VERSION_COVERAGE; targetVersion <= appInfo.getGoogleAppVersion(); targetVersion++) {
			Date lastReviewDate = androidAppReviewDao.getLastReviewDateFromDbForTargetVersion(appInfo.getAppId(), targetVersion);
			insertReviewListForTargetVersion(targetVersion, lastReviewDate, appInfo);
		}
	}

	private void insertReviewListForTargetVersion(int targetAppVersion, Date lastReviewDate, AppInfo appInfo) throws Exception, JSONException, ParseException {
		for (int pageNum = 0; pageNum <= END_OF_PAGE; pageNum++) {
			List<AndroidAppReview> reviewList = new ArrayList<AndroidAppReview>();

			JSONArray reviewJsonArray = getReviewJsonArrayOfPage(pageNum, targetAppVersion, appInfo);
			log.info("JSON 응답 상태 :: " + reviewJsonArray.getInt(INDEX_OF_JSON_STATUS));

			if (reviewJsonArray.getInt(INDEX_OF_JSON_STATUS) == GOOGLE_JSON_STATUS_PAGE_NOT_FOUND)
				return;

			Document googlePlayDocument = Jsoup.parse(reviewJsonArray.get(INDEX_OF_JSON_BODY).toString());
			Elements reviewElements = googlePlayDocument.select(".single-review");

			for (int reviewElementIndex = 0; reviewElementIndex < reviewElements.size(); reviewElementIndex++) {
				Element reviewElement = reviewElements.get(reviewElementIndex);

				Element reviewDate = reviewElement.select(".review-date").first();
				Date parsedDate = new SimpleDateFormat("yyyy년 M월 d일").parse(reviewDate.ownText());

				if (isInDateCoverage(reviewDate, parsedDate, lastReviewDate) == false) {
					insertReviewList(reviewList);
					return;
				}

				Element author = reviewElement.select(".author-name").first();
				Element reviewLink = reviewElement.select(".reviews-permalink").first();
				Element starScore = reviewElement.select(".current-rating").first();
				Element reviewTitle = reviewElement.select(".review-title").first();
				Element reviewBody = reviewElement.select(".review-body").first();

				AndroidAppReview androidAppReview = new AndroidAppReview();
				if (author.children().size() != 0)
					androidAppReview.setUserLink(GOOGLE_PLAY + author.child(0).attr("href"));
				androidAppReview.setUserName(author.text());
				androidAppReview.setReviewDate(new SimpleDateFormat("yyyy-MM-dd").format(parsedDate));
				androidAppReview.setReviewLink(GOOGLE_PLAY + reviewLink.attr("href"));
				androidAppReview.setStarScore(parseStarScore(starScore));
				androidAppReview.setReviewTitle(reviewTitle.ownText());
				androidAppReview.setReviewContent(reviewBody.ownText());
				androidAppReview.setAndroidAppId(appInfo.getAppId());
				androidAppReview.setGoogleAppVersion(targetAppVersion);

				reviewList.add(androidAppReview);
			}

			insertReviewList(reviewList);

			if (reviewJsonArray.getInt(INDEX_OF_JSON_STATUS) == GOOGLE_JSON_STATUS_LAST_PAGE)
				return;
		}
	}

	private void insertReviewList(List<AndroidAppReview> reviewList) {
		if (reviewList.size() > 0)
			androidAppReviewDao.insertAndroidAppReviewList(reviewList);
	}

	private boolean isInDateCoverage(Element reviewDate, Date parsedDate, Date lastReviewDate) throws ParseException {
		if (lastReviewDate == null)
			return true;

		return lastReviewDate.getTime() - parsedDate.getTime() < TIME_MILLIS_FOR_A_DAY * DEFAULT_DATE_COVERAGE;
	}

	private int parseStarScore(Element starScore) {
		return Integer.parseInt(starScore.attr("style").split("%")[0].substring(7)) / 20;
	}

	private JSONArray getReviewJsonArrayOfPage(int pageNum, int targetAppVersion, AppInfo appInfo) throws Exception {
		HttpURLConnection httpConnectionForReview = makeConnectionForReview();
		makeRequestBody(pageNum, targetAppVersion, appInfo, httpConnectionForReview);

		return getJsonArrayResponse(httpConnectionForReview);
	}

	private JSONArray getJsonArrayResponse(HttpURLConnection httpConnectionForReview) throws Exception {
		String reviewJsonArrayString = null;
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(httpConnectionForReview.getInputStream())));

			while ((reviewJsonArrayString = bufferedReader.readLine()) != null)
				if (reviewJsonArrayString.startsWith("["))
					break;

		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}

		return new JSONArray(reviewJsonArrayString.substring(1));
	}

	private void makeRequestBody(int pageNum, int targetAppVersion, AppInfo appInfo, HttpURLConnection httpConnectionForReview) throws Exception {
		PrintWriter printWriter = null;

		try {
			printWriter = new PrintWriter(httpConnectionForReview.getOutputStream());
			printWriter.write("reviewType=0&xhr=1&reviewSortOrder=0&id=" + appInfo.getAppId() + "&version=" + targetAppVersion + "&pageNum=" + pageNum);
			log.info("JSON 응답 요청 :: " + "reviewType=0&xhr=1&reviewSortOrder=0&id=" + appInfo.getAppId() + "&version=" + targetAppVersion + "&pageNum=" + pageNum);
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	private HttpURLConnection makeConnectionForReview() throws Exception {
		HttpURLConnection httpConnectionForReview = (HttpURLConnection) new URL(REVIEW_REQUEST_URL).openConnection();
		httpConnectionForReview.setRequestMethod("POST");
		httpConnectionForReview.setDoOutput(true);
		httpConnectionForReview.setRequestProperty("Connection", "keep-alive");
		httpConnectionForReview.setRequestProperty("Origin", "https://play.google.com");
		httpConnectionForReview.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
		httpConnectionForReview.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpConnectionForReview.setRequestProperty("Accept", "*/*");
		httpConnectionForReview.setRequestProperty("Referer", "https://play.google.com/store/apps/details?id=com.tmon");
		httpConnectionForReview.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		httpConnectionForReview.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
		httpConnectionForReview.setRequestProperty("Cookie", COOKIE_GOOGLE_ABUSE_EXEMPTION);

		return httpConnectionForReview;
	}

	private void checkLatestVersion(AppInfo appInfo) throws Exception {
		AppInfo appInfoFromGoogle = getLatestInfoFromGoogle(appInfo.getAppId());

		if (appInfo.getGoogleAppVersion() < appInfoFromGoogle.getGoogleAppVersion())
			appInfoDao.insertVersion(appInfoFromGoogle);

		appInfo = appInfoFromGoogle;
	}

	private AppInfo getLatestInfoFromGoogle(String appId) throws Exception {
		AppInfo latestAppInfo = new AppInfo();
		HttpURLConnection httpConnectionForVersionInfo = makeConnectionForVersionInfo(appId);
		Document googlePlayDocument = getDocumentFromVersionInfoConnection(httpConnectionForVersionInfo);

		return parseAppInfoFromDocument(appId, latestAppInfo, googlePlayDocument);
	}

	private AppInfo parseAppInfoFromDocument(String appId, AppInfo latestAppInfo, Document googlePlayDocument) {
		Element averageScore = googlePlayDocument.select(".score").first();
		appInfoDao.updateAverageScore(appId, averageScore.text());

		Element appVersion = googlePlayDocument.select("div[itemprop=softwareVersion]").first();
		Element googleAppVersion = googlePlayDocument.select("div .dropdown-child").last();

		latestAppInfo.setAppId(appId);
		latestAppInfo.setAndroidAppVersion(appVersion.text());
		latestAppInfo.setGoogleAppVersion(Integer.parseInt(googleAppVersion.attr("data-dropdown-value")));

		return latestAppInfo;
	}

	private Document getDocumentFromVersionInfoConnection(HttpURLConnection httpConnectionForVersionInfo) throws IOException {
		StringBuilder googlePlayHtml = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(httpConnectionForVersionInfo.getInputStream())));
			String line = null;

			while ((line = bufferedReader.readLine()) != null)
				googlePlayHtml.append(line).append("\n");

		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}

		return Jsoup.parse(googlePlayHtml.toString());
	}

	private HttpURLConnection makeConnectionForVersionInfo(String appId) throws IOException, MalformedURLException, ProtocolException {
		HttpURLConnection httpConnectionForGooglePlay = (HttpURLConnection) new URL(GOOGLE_PLAY_REQUEST_URL + appId).openConnection();
		httpConnectionForGooglePlay.setRequestMethod("GET");
		httpConnectionForGooglePlay.setRequestProperty("Connection", "keep-alive");
		httpConnectionForGooglePlay.setRequestProperty("Cache-Control", "max-age=0");
		httpConnectionForGooglePlay.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpConnectionForGooglePlay.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
		httpConnectionForGooglePlay.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		httpConnectionForGooglePlay.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
		httpConnectionForGooglePlay.setRequestProperty("Cookie", COOKIE_GOOGLE_ABUSE_EXEMPTION);

		return httpConnectionForGooglePlay;
	}
}
