package br.com.preventsenior.exam.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.preventsenior.exam.model.Log;

public class LogResult {

    @JsonInclude(Include.NON_NULL)
	private Long totalPages;

    @JsonInclude(Include.NON_NULL)
	private List<Log> records;

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public List<Log> getRecords() {
		return records;
	}

	public void setRecords(List<Log> records) {
		this.records = records;
	}
	
	
	
}
