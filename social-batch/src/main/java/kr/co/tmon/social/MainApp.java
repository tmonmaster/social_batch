package kr.co.tmon.social;

import java.util.Date;

import kr.co.tmon.social.batch.controller.BatchController;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
public class MainApp {

	private static final int DEFAULT_MINUTE_FOR_NEWS = 10;
	private static final int DEFAULT_HOUR_FOR_REVIEW = 12;

	private static final int MINUTE = 1000 * 60;
	private static final int HOUR = 1000 * 60 * 60;

	private static final ApplicationContext applicationContext;
	private static final TaskScheduler scheduler;
	private static final BatchController batchController;

	private static Logger logger = Logger.getLogger(MainApp.class);

	static {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		scheduler = applicationContext.getBean("taskScheduler", ThreadPoolTaskScheduler.class);
		batchController = applicationContext.getBean("batchController", BatchController.class);
	}

	public static void main(String[] args) {
		Runnable newsBatchTask = new NewsBatchTask();
		Runnable androidReviewTask = new AndroidReviewBatchTask();
		Runnable androidRankingTask = new AndroidRankingBatchTask();

		Date currentDate = new Date();

		scheduler.scheduleWithFixedDelay(newsBatchTask, currentDate, MINUTE * DEFAULT_MINUTE_FOR_NEWS);
		scheduler.scheduleWithFixedDelay(androidReviewTask, currentDate, HOUR * DEFAULT_HOUR_FOR_REVIEW);
		scheduler.scheduleWithFixedDelay(androidRankingTask, currentDate, HOUR);
	}

	private static class NewsBatchTask implements Runnable {
		@Override
		public void run() {
			try {
				batchController.doNewsBatch();
			} catch (Exception e) {
				MainApp.logger.error("뉴스 수집 실패", e);
				throw new RuntimeException(e);
			}
		}
	}

	private static class AndroidReviewBatchTask implements Runnable {
		@Override
		public void run() {
			try {
				batchController.doAndroidReviewBatch();
			} catch (Exception e) {
				MainApp.logger.error("앱 리뷰 수집 실패", e);
				throw new RuntimeException(e);
			}
		}
	}

	private static class AndroidRankingBatchTask implements Runnable {
		@Override
		public void run() {
			try {
				batchController.doAndroidRankingBatch();
			} catch (Exception e) {
				MainApp.logger.error("앱 랭킹 작업 실패", e);
				throw new RuntimeException(e);
			}
		}
	}
}
