package br.com.preventsenior.exam.model.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.preventsenior.exam.model.Log;

@RestController
@RequestMapping("/log")
@CrossOrigin("*")
public class LogRestController {

	@GetMapping
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
