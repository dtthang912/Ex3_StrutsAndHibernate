package com.fsoft.thangdt3.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public final class ViewUtil {
	public static final int RECORDS_PER_PAGE = 5;
	public static final int DISPLAY_PAGES = 5;

	private ViewUtil() {

	}

	public static List<Integer> getPageIndexes(int currentPageIndex, int maxPageIndex) {
		List<Integer> pageIndexes = new ArrayList<>();
		int minDisplayIndex = 0;
		int maxDisplayIndex = 0;
		
		if ((maxPageIndex - currentPageIndex) <= (DISPLAY_PAGES / 2)) {
			minDisplayIndex = maxPageIndex < DISPLAY_PAGES ? 1 : maxPageIndex - DISPLAY_PAGES;
			maxDisplayIndex = maxPageIndex;
		} else if (currentPageIndex <= DISPLAY_PAGES / 2 + 1) {
			minDisplayIndex = 1;
			maxDisplayIndex = DISPLAY_PAGES;
		} else {
			minDisplayIndex = currentPageIndex - DISPLAY_PAGES / 2;
			maxDisplayIndex = minDisplayIndex + DISPLAY_PAGES - 1;
		}

		for (int i = minDisplayIndex; i <= maxDisplayIndex; i++) {
			pageIndexes.add(i);
		}
		return pageIndexes;
	}

	public static String encodeURI(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8");
	}
}
