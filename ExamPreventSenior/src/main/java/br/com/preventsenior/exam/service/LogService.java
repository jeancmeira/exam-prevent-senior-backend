package br.com.preventsenior.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.preventsenior.exam.model.Log;
import br.com.preventsenior.exam.repository.LogRepository;

@Service
public class LogService {

	private static final int RECORDS_BY_PAGE = 1;
	
	@Autowired
	private LogRepository logRepository;
	
	public List<Log> list(Integer page) {
		Pageable pageable = PageRequest.of(page - 1, RECORDS_BY_PAGE, Sort.by("date").descending());
		Page<Log> recordsPage = logRepository.findAll(pageable);
		return recordsPage.getContent();
	}

	
}
