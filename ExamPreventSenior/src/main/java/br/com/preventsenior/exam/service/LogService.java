package br.com.preventsenior.exam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.preventsenior.exam.model.Log;

@Service
public class LogService {

	public List<Log> list() {
		List<Log> list = new ArrayList<>();
		
		Log log = new Log();
		log.setId(1L);
		log.setDate(new Date());
		log.setRequest("sadsadsa");
		list.add(log);
		
		return list;
	}

	
}
