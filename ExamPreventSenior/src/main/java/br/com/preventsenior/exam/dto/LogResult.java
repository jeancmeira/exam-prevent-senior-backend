package br.com.preventsenior.exam.dto;

import java.util.List;

import br.com.preventsenior.exam.model.Log;

public class LogResult {

	private Integer totalPages;
	
	private List<Log> records;

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<Log> getRecords() {
		return records;
	}

	public void setRecords(List<Log> records) {
		this.records = records;
	}
	
	
	
}
