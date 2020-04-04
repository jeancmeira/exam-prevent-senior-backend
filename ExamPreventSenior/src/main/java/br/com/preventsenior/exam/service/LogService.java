package br.com.preventsenior.exam.service;

import java.util.List;
import java.util.Optional;

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

	private static final int RECORDS_BY_PAGE = 10;
	
	@Autowired
	private LogRepository logRepository;
	
	public List<Log> list(Integer page) {
		Pageable pageable = PageRequest.of(page - 1, RECORDS_BY_PAGE, Sort.by("date").descending());
		Page<Log> recordsPage = logRepository.findAll(pageable);
		return recordsPage.getContent();
	}
	
	public Long count() {
		return logRepository.count();
	}
	
	public void save(Log log) {

		if (log.getDate() == null) {
			throw new RuntimeException("Date is required.");
		}
		
		if (log.getIp() == null || log.getIp().trim().equals("")) {
			throw new RuntimeException("Ip is required.");
		}
		
		if (log.getRequest() == null || log.getRequest().trim().equals("")) {
			throw new RuntimeException("Request is required.");
		}
		
		if (log.getUserAgent() == null || log.getUserAgent().trim().equals("")) {
			throw new RuntimeException("User agent is required.");
		}

		if (log.getStatus() == null) {
			throw new RuntimeException("Status is required.");
		}

		
		logRepository.save(log);
	}

	public void delete(Log log) {
		logRepository.delete(log);
	}

	public Log find(Long id) {
		Optional<Log> result = logRepository.findById(id);
		
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	
}
