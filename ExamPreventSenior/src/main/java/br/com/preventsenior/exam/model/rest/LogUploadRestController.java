package br.com.preventsenior.exam.model.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/log-upload")
@CrossOrigin("*")
public class LogUploadRestController {

	@PostMapping
	public Boolean handleFileUpload(@RequestParam("file") MultipartFile file) {

		System.out.println(file.getName());
		
		return Boolean.TRUE;
	}
	
}
