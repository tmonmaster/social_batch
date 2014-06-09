package kr.co.tmon.social.filter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class FilteringControllerTest {

	@Autowired
	private FilteringController filteringController;

	@Test
	public void testApplyFilter() throws Exception {
		filteringController.applyFilter(FilteringController.FILTER_ALL);
	}

}
