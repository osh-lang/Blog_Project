package org.project.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostPaging {
	/** 현재 페이지 번호 */
	private int currentPageNo; // 처음에는 1이고, 뷰에서 page번호를 받음

	/** 페이지당 출력할 데이터 개수 */
	private int recordsPerPage; // 10으로 고정

	/** 태그 */
	private int tag;

	/** 태그 카운트*/
	private int tagCount;

	/** 카테고리 */
	private String[] category;

	public PostPaging() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
	}

	public int getStartPage() { // startPage를 LIMIT에 넘김
		return (currentPageNo - 1) * recordsPerPage;
	}
}
