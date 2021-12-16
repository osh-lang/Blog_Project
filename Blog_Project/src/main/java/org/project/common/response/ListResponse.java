package org.project.common.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResponse<T> extends CommonResponse {
	private List<T> data;
	private int total_count;
	private int search_count;
	private int search_page;
	private int total_count_cnt;
}
