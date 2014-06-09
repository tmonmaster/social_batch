package kr.co.tmon.social.filter.controller;

import kr.co.tmon.social.filter.service.FilteringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
@Controller
public class FilteringController {
	public static final String FILTER_ALL = "all";

	@Autowired
	private FilteringService filteringService;

	public void applyFilter(String date) throws Exception {
		if (date != null && date.equalsIgnoreCase(FILTER_ALL))
			filteringService.startNewsFiltering(FILTER_ALL);
		else
			filteringService.startNewsFiltering(date);
	}
}
