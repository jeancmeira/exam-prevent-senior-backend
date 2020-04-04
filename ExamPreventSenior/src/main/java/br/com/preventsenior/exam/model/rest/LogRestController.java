package br.com.preventsenior.exam.model.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.preventsenior.exam.common.DateFormatConstant;
import br.com.preventsenior.exam.model.Log;
import br.com.preventsenior.exam.service.LogService;
import br.com.preventsenior.exam.vo.LogResult;

@RestController
@RequestMapping("/log")
@CrossOrigin("*")
public class LogRestController {

	@Autowired
	private LogService logService;
	
	@GetMapping
	public LogResult list(@RequestParam Integer page, 
			@RequestParam(required=false) String ip
			,
			@RequestParam(required=false) 
			@DateTimeFormat(pattern=DateFormatConstant.DATE_TIME_FORMAT)
			Date startDate
			,
			@RequestParam(required=false)
			@DateTimeFormat(pattern=DateFormatConstant.DATE_TIME_FORMAT)
			Date endDate
			) {
		
		return logService.search(page, ip, startDate, endDate);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Log> find(@PathVariable Long id) {
		Log log = logService.find(id);
		
		if (log != null)
			return new ResponseEntity<Log>(log, HttpStatus.OK);
		else
			return new ResponseEntity<Log>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public Long save(@RequestBody Log log) {
		logService.save(log);
		return log.getId();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		
		Log log = logService.find(id);
		
		if (log != null) {
			logService.delete(log);
			return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_FOUND);
		}
	}

}
