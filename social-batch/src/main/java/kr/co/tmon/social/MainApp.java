package kr.co.tmon.social;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.tmon.social.batch.controller.BatchController;
import kr.co.tmon.social.filter.controller.FilteringController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
public class MainApp {

	private static final int MINUTE = 1000 * 60;
	private static final int HOUR = 1000 * 60 * 60;

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		TaskScheduler scheduler = applicationContext.getBean("taskScheduler", ThreadPoolTaskScheduler.class);
		BatchController batchController = applicationContext.getBean("batchController", BatchController.class);
		FilteringController filteringController = applicationContext.getBean("filteringController", FilteringController.class);

		Runnable newsBatchTask = makeNewsBatchTask(batchController);
		Runnable androidReviewTask = makeAndroidReviewBatchTask(batchController);
		Runnable filteringTask = makeFilteringTask(filteringController);

		Date currentDate = new Date();

		scheduler.scheduleWithFixedDelay(newsBatchTask, currentDate, MINUTE * 10);
		scheduler.scheduleWithFixedDelay(filteringTask, currentDate, MINUTE * 10);
		scheduler.scheduleWithFixedDelay(androidReviewTask, currentDate, HOUR * 12);
	}

	private static Runnable makeNewsBatchTask(final BatchController batchController) {
		Runnable newsBatchRun = new Runnable() {
			@Override
			public void run() {
				try {
					batchController.doNewsBatch();
				} catch (Exception e) {
					throw new RuntimeException("뉴스배치작업", e);
				}
			}
		};

		return newsBatchRun;
	}

	private static Runnable makeAndroidReviewBatchTask(final BatchController batchController) {
		Runnable androidReviewTask = new Runnable() {

			@Override
			public void run() {
				try {
					batchController.doAndroidReviewBatch();
				} catch (Exception e) {
					throw new RuntimeException("안드로이드 리뷰 배치", e);
				}
			}
		};
		return androidReviewTask;
	}

	private static Runnable makeFilteringTask(final FilteringController filteringController) {
		Runnable filteringTask = new Runnable() {

			@Override
			public void run() {
				try {
					filteringController.applyFilter(getCurrentTimeString());
				} catch (Exception e) {
					throw new RuntimeException("필터링", e);
				}
			}

			private String getCurrentTimeString() {
				return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}
		};
		return filteringTask;
	}
}
