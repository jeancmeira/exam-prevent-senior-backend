package br.com.preventsenior.exam.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
/**
 * Classe service
 * @author Jean
 *
 */
public class LogService {

	private static final Integer RECORDS_BY_PAGE = 10;
	
	@Autowired
	private LogRepository logRepository;
	
	/**
	 * Metodo que busca os logs
	 * @param page
	 * @param ip
	 * @param startDate
	 * @param endDate
	 * @return
	 */
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
			recordsPage = logRepository.findAll(ip, getDateWithSeconds(startDate, 0), getDateWithSeconds(endDate,999), pageable);
		}

		return new Result<Log>(recordsPage.getTotalPages(), 
					recordsPage.getContent());
	}

	/**
	 * Metodo que busca o dashboard
	 * @param page
	 * @return
	 */
	public Result<LogAggregation> searchAggregations(Integer page) {
		
		Pageable pageable = getPageable(page, "ip");
		
		Page<LogAggregation> recordsPage = logRepository.findAllAggregations(pageable);
		
		return new Result<LogAggregation>(recordsPage.getTotalPages(), 
					recordsPage.getContent());
	}
	
	/**
	 * Metodo que salva
	 * @param log
	 */
	public void save(Log log) {

		if (log.getDate() == null) {
			throw new RuntimeException("Favor informar a data.");
		}
		
		if (log.getIp() == null || log.getIp().trim().equals("")) {
			throw new RuntimeException("Favor informar o IP.");
		}
		
		if (log.getRequest() == null || log.getRequest().trim().equals("")) {
			throw new RuntimeException("Favor informar o request.");
		}
		
		if (log.getUserAgent() == null || log.getUserAgent().trim().equals("")) {
			throw new RuntimeException("Favor informar o User agent.");
		}

		if (log.getStatus() == null) {
			throw new RuntimeException("Favor informar o Status.");
		}

		
		logRepository.save(log);
	}

	/**
	 * Metodo que exclui
	 * @param log
	 */
	public void delete(Log log) {
		logRepository.delete(log);
	}

	@Transactional
	/**
	 * Metodo que adiciona os logs
	 * @param logs
	 */
	public void addLogs(List<Log> logs) {
		
		if (logs == null || logs.isEmpty()) {
			throw new RuntimeException("Os logs são requeridos.");
		}
		
		for (Log log : logs) {

			save(log);
			
		}
		
	}
	
	/**
	 * Metodo que busca por id
	 * @param id
	 * @return
	 */
	public Log find(Long id) {
		Optional<Log> result = logRepository.findById(id);
		
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	/**
	 * Metodo que valida os parametros
	 * @param ip
	 * @param startDate
	 * @param endDate
	 */
	private void validateQueryParameters(String ip, Date startDate, Date endDate) {
		if (ip != null &&  !ip.trim().equals("")) {
			
			if (startDate == null || endDate == null) {
				throw new RuntimeException("Favor informar Data inicial e final.");	
			}
			
		}
	}
	
	/**
	 * Metodo que cria o obeto de paginacao
	 * @param page
	 * @param sortField
	 * @return
	 */
	private Pageable getPageable(Integer page, String sortField) {
		Pageable pageable = PageRequest.of(page - 1,
					RECORDS_BY_PAGE, Sort.by(sortField).descending());
		return pageable;
	}

	/**
	 * Definir miliseconds
	 * @param endDate
	 * @param i
	 * @return
	 */
	private Date getDateWithSeconds(Date date, int miliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, miliseconds);
		return c.getTime();
	}

	
}
