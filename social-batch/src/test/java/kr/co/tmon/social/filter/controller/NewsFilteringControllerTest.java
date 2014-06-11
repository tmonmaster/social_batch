package kr.co.tmon.social.filter.controller;

import kr.co.tmon.social.filter.constant.FilteringConstant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class NewsFilteringControllerTest {

	@Autowired
	private NewsFilteringController filteringController;

	@Test
	public void testApplyFilter() throws Exception {
		filteringController.applyFilter(FilteringConstant.FILTER_ALL);
	}

}
