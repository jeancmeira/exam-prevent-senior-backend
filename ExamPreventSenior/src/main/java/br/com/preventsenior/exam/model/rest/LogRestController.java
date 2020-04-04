package br.com.preventsenior.exam.model.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.preventsenior.exam.model.Log;
import br.com.preventsenior.exam.service.LogService;

@RestController
@RequestMapping("/log")
@CrossOrigin("*")
public class LogRestController {

	@Autowired
	private LogService logService;
	
	@GetMapping
	public List<Log> list(@RequestParam Integer page) {
		return logService.list(page);
	}
	
}
