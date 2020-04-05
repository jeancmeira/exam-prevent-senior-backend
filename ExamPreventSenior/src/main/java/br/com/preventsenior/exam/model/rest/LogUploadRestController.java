package br.com.preventsenior.exam.model.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.preventsenior.exam.common.DateFormatConstant;
import br.com.preventsenior.exam.model.Log;

@RestController
@RequestMapping("/log-upload")
@CrossOrigin("*")
public class LogUploadRestController {

	@PostMapping
	public Boolean handleFileUpload(@RequestParam("file") MultipartFile file) {

		try {
			List<Log> list = readLines(file.getBytes());
		} catch (RuntimeException e) {			
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return Boolean.TRUE;
	}
	
	private List<Log> readLines(byte[] bytes) {
		List<Log> list = new ArrayList<>();
		
		InputStream is = null;
        BufferedReader bfReader = null;
        try {
            is = new ByteArrayInputStream(bytes);
            bfReader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            
            int index = 0;
            
            while((line = bfReader.readLine()) != null){
               
            	String[] parts = line.split("\\|");
            	
            	if (parts.length < 5) {
            		throw new RuntimeException("Invalid log file format.");
            	}
            	
            	Date date = convertToDate(parts[0]);
            	
            	String ip = parts[1];
            	
            	String request = removeDoubleQuotes(parts[2]);
            	
            	Integer status = convertToInteger(parts[3]);
            	
            	String userAgent = removeDoubleQuotes(parts[4]);

            	index++;
            	
            	if (date == null) {
            		throw new RuntimeException("Invalid date format in line [" + index + "] .");
            	}
            	
            	if (ip == null) {
            		throw new RuntimeException("Invalid ip format in line [" + index + "] .");
            	}
            	
            	if (request == null) {
            		throw new RuntimeException("Invalid ip request in line [" + index + "] .");
            	}

            	if (status == null) {
            		throw new RuntimeException("Invalid ip status in line [" + index + "] .");
            	}

            	if (userAgent == null) {
            		throw new RuntimeException("Invalid ip userAgent in line [" + index + "] .");
            	}

            	System.out.println(date);
            	System.out.println(ip);
            	System.out.println(request);
            	System.out.println(status);
            	System.out.println(userAgent);
            	System.out.println();
            	
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(bfReader != null) bfReader.close();
                
                if(is != null) is.close();
            } catch (Exception ex){
                 
            }
        }
		return list;
	}

	private Integer convertToInteger(String value) {
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Date convertToDate(String value) {
		try {
			return new SimpleDateFormat(DateFormatConstant.FILE_DATE_TIME_FORMAT).parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	private String removeDoubleQuotes(String value) {
		if (value == null || value.trim().length() < 3) {
			return null;
		}
		
		if (value.charAt(0) != '"') {
			return null;
		}
		
		if (value.charAt(value.length() - 1) != '"') {
			return null;
		}

		String newValue = value.substring(1, value.length() - 1);
		return newValue;
	}
	
}
