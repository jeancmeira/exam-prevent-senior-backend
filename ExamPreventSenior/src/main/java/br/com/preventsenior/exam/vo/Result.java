package br.com.preventsenior.exam.vo;

import java.util.List;

public class Result<T> {

	private Integer totalPages;

	private List<T> records;
	
	public Result(Integer totalPages, List<T> records) {
		this.totalPages = totalPages;
		this.records = records;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public List<T> getRecords() {
		return records;
	}

}
