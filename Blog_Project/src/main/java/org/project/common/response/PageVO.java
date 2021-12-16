package org.project.common.response;

import lombok.Getter;

@Getter
public class PageVO {
	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private PostPaging postPaging;

	public PageVO(PostPaging postPaging, int total) {
		this.postPaging = postPaging;
		this.total = total;

		this.endPage = (int)(Math.ceil(postPaging.getCurrentPageNo() / 10.0)) * 10;
		this.startPage = this.endPage -9;

		int realEnd = (int)(Math.ceil((total * 1.0) / postPaging.getRecordsPerPage()));

		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
