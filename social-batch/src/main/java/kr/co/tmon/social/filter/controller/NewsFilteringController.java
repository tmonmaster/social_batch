package kr.co.tmon.social.filter.controller;

import kr.co.tmon.social.filter.constant.FilteringConstant;
import kr.co.tmon.social.filter.service.NewsFilteringService;
import kr.co.tmon.social.filter.service.SingleNewsFilteringService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 정승현 - RASPILLA16@tmon.co.kr
 * 
 */
@Controller
public class NewsFilteringController {
	@Autowired
	private NewsFilteringService newsFilteringService;
	@Autowired
	private SingleNewsFilteringService singleNewsFilteringService;

	public void applyFilter(String date) throws Exception {
		if (date != null && date.equalsIgnoreCase(FilteringConstant.FILTER_ALL)) {
			newsFilteringService.startNewsFiltering(FilteringConstant.FILTER_ALL);
			singleNewsFilteringService.startSingleNewsFiltering(FilteringConstant.FILTER_ALL);
		} else {
			newsFilteringService.startNewsFiltering(date);
			singleNewsFilteringService.startSingleNewsFiltering(date);
		}
	}
}
