package br.com.preventsenior.exam.model.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.preventsenior.exam.service.LogService;
import br.com.preventsenior.exam.vo.LogAggregation;
import br.com.preventsenior.exam.vo.Result;

@RestController
@RequestMapping("/log-aggregation")
@CrossOrigin("*")
public class LogAggregationRestController {

	@Autowired
	private LogService logService;
	
	@GetMapping
	public Result<LogAggregation> search(@RequestParam Integer page) {
		return logService.searchAggregations(page);
	}
	
}
