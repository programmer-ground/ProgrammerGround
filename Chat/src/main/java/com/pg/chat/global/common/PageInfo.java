package com.pg.chat.global.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
	private final int currentPage;
	private final int currentSize;
	private final int totalPage;
	private final long totalElements;

	@Builder
	public PageInfo(int currentPage, int currentSize, int totalPage, long totalElements) {
		this.currentPage = currentPage + 1;
		this.currentSize = currentSize;
		this.totalPage = totalPage;
		this.totalElements = totalElements;
	}

	@Override
	public String toString() {
		return "PageInfo{" +
			"currentPage=" + currentPage +
			", currentSize=" + currentSize +
			", totalPage=" + totalPage +
			", totalElements=" + totalElements +
			'}';
	}
}
