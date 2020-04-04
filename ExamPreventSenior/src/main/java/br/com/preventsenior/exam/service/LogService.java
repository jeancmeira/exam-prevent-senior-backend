package br.com.preventsenior.exam.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.preventsenior.exam.model.Log;
import br.com.preventsenior.exam.repository.LogRepository;
import br.com.preventsenior.exam.vo.LogAggregation;
import br.com.preventsenior.exam.vo.Result;

@Service
public class LogService {

	private static final Integer RECORDS_BY_PAGE = 10;
	
	@Autowired
	private LogRepository logRepository;
	
	public Result<Log> search(
			Integer page,
			String ip,
			Date startDate,
			Date endDate) {
		
		validateQueryParameters(ip, startDate, endDate);
		
		Pageable pageable = getPageable(page, "date");
		Page<Log> recordsPage = null;
		
		if (ip == null) {
			recordsPage = logRepository.findAll(pageable);	
		} else {
			recordsPage = logRepository.findAll(ip, startDate, endDate, pageable);
		}

		return new Result<Log>(recordsPage.getTotalPages(), 
					recordsPage.getContent());
	}

	public Result<LogAggregation> search(Integer page) {
		
		Pageable pageable = getPageable(page, "ip");
		
		Page<LogAggregation> recordsPage = logRepository.listTotalization(pageable);
		
		return new Result<LogAggregation>(recordsPage.getTotalPages(), 
					recordsPage.getContent());
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

	private void validateQueryParameters(String ip, Date startDate, Date endDate) {
		if (ip != null &&  !ip.trim().equals("")) {
			
			if (startDate == null || endDate == null) {
				throw new RuntimeException("startDate and endDate are required.");	
			}
			
		}
	}
	
	private Pageable getPageable(Integer page, String sortField) {
		Pageable pageable = PageRequest.of(page - 1,
					RECORDS_BY_PAGE, Sort.by(sortField).descending());
		return pageable;
	}
	
}
