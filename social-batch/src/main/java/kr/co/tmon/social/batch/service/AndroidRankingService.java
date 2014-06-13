package kr.co.tmon.social.batch.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.zip.GZIPInputStream;

import kr.co.tmon.social.batch.dao.AndroidRankingDao;
import kr.co.tmon.social.batch.dao.AppInfoDao;
import kr.co.tmon.social.batch.vo.AppInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Yunho Lee (forfle@tmon.co.kr)
 * 
 */
@Service
public class AndroidRankingService {

	private static final String ANDROID_RANKING_URL = "https://play.google.com/store/apps/collection/topselling_free";
	private static final int PAGE_LIMIT = 8;
	private static final int PAGE_SIZE = 60;

	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private AndroidRankingDao androidRankingDao;

	public int insertRanking() throws Exception {
		List<AppInfo> appInfoList = appInfoDao.getAppInfoList();

		for (int page = 0; page < PAGE_LIMIT; page++) {
			HttpURLConnection androidRankingConnection = getAndroidRankingConnection();
			initRequestBody(androidRankingConnection, page);

			StringBuilder androidRankingHtmlString = getAndroidRankingHtmlString(androidRankingConnection);
			initAppRanking(page, appInfoList, androidRankingHtmlString);
		}

		return androidRankingDao.insertRanking(appInfoList);
	}

	private void initAppRanking(int page, List<AppInfo> appInfoList, StringBuilder androidRankingHtmlString) {
		Document androidRanking = Jsoup.parse(androidRankingHtmlString.toString());
		Elements androidAppElements = androidRanking.select("a.title");

		for (int androidAppIndex = 0; androidAppIndex < androidAppElements.size(); androidAppIndex++) {
			Element androidAppElement = androidAppElements.get(androidAppIndex);

			String appId = androidAppElement.attr("href");

			for (AppInfo appInfo : appInfoList)
				if (appId.endsWith(appInfo.getAppId()))
					appInfo.setRanking(androidAppIndex + 1 + page * PAGE_SIZE);
		}
	}

	private StringBuilder getAndroidRankingHtmlString(HttpURLConnection androidRankingConnection) throws IOException {
		StringBuilder androidRankingHtmlString = new StringBuilder();
		String line = null;
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(androidRankingConnection.getInputStream())));

			while ((line = bufferedReader.readLine()) != null)
				androidRankingHtmlString.append(line).append("\n");

		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}

		return androidRankingHtmlString;
	}

	private void initRequestBody(HttpURLConnection androidRankingConnection, int page) throws IOException {
		PrintWriter printWriter = null;

		try {
			printWriter = new PrintWriter(androidRankingConnection.getOutputStream());
			printWriter.write("ipf=1&xhr=1&num=60&numChildren=0&start=" + (page * PAGE_SIZE));
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}

	private HttpURLConnection getAndroidRankingConnection() throws Exception {
		HttpURLConnection androidRankingConnection = (HttpURLConnection) new URL(ANDROID_RANKING_URL).openConnection();
		androidRankingConnection.setRequestMethod("POST");
		androidRankingConnection.setDoOutput(true);
		androidRankingConnection.setRequestProperty("Connection", "keep-alive");
		androidRankingConnection.setRequestProperty("Origin", "https://play.google.com");
		androidRankingConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36");
		androidRankingConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		androidRankingConnection.setRequestProperty("Accept", "*/*");
		androidRankingConnection.setRequestProperty("Referer", "https://play.google.com/store/apps/collection/topselling_free");
		androidRankingConnection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		androidRankingConnection.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");

		return androidRankingConnection;
	}
}
