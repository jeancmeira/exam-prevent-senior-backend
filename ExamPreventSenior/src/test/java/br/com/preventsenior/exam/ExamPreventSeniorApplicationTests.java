package br.com.preventsenior.exam;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.preventsenior.exam.model.Log;
import br.com.preventsenior.exam.service.LogService;
import br.com.preventsenior.exam.vo.LogAggregation;
import br.com.preventsenior.exam.vo.Result;

@SpringBootTest
@SpringBootApplication
class ExamPreventSeniorApplicationTests {

	@Autowired
	private LogService logService;
	
	@Test
	public void testSaveLog() {
		
		Log log = new Log();
		log.setDate(new Date());
		log.setIp("127.0.0.1");
		log.setRequest("GET /PATH");
		log.setStatus(200);
		log.setUserAgent("Mozilla");
		
		logService.save(log);
		
		assertNotNull(log.getId());
	}

	@Test
	public void testDeleteLog() {
		
		Log log = new Log();
		log.setDate(new Date());
		log.setIp("127.0.0.1");
		log.setRequest("GET /PATH");
		log.setStatus(200);
		log.setUserAgent("Mozilla");
		
		logService.save(log);
		
		logService.delete(log);
		
		assertNull(logService.find(log.getId()));
	}

	@Test
	public void testFindLog() {
		
		Log log = new Log();
		log.setDate(new Date());
		log.setIp("127.0.0.1");
		log.setRequest("GET /PATH");
		log.setStatus(200);
		log.setUserAgent("Mozilla");
		
		logService.save(log);
		
		assertNotNull(logService.find(log.getId()));
	}

	@Test
	public void testSearchLog() {
		Date date = new Date();
		
		Log log = new Log();
		log.setDate(date);
		log.setIp("127.0.0.1");
		log.setRequest("GET /PATH");
		log.setStatus(200);
		log.setUserAgent("Mozilla");
		
		logService.save(log);
		
		Result<Log> result = logService.search(1, "127.0.0.1", date, date);
		assertFalse(result.getRecords().isEmpty());
	}

	@Test
	public void testSearchAggregationLog() {
		Date date = new Date();
		
		Log log = new Log();
		log.setDate(date);
		log.setIp("127.0.0.1");
		log.setRequest("GET /PATH");
		log.setStatus(200);
		log.setUserAgent("Mozilla");
		
		logService.save(log);
		
		Result<LogAggregation> result = logService.searchAggregations(1);
		assertFalse(result.getRecords().isEmpty());
	}

}
