package br.com.preventsenior.exam.vo;

import java.util.List;

import br.com.preventsenior.exam.model.Log;

public class LogResult {

	private Integer totalPages;

	private List<Log> records;
	
	public LogResult(Integer totalPages, List<Log> records) {
		this.totalPages = totalPages;
		this.records = records;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public List<Log> getRecords() {
		return records;
	}
	
}
