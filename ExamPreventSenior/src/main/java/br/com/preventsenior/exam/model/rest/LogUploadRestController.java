package br.com.preventsenior.exam.model.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            while((line = bfReader.readLine()) != null){
               
            	String[] parts = line.split("\\|");
            	
            	if (parts.length < 5) {
            		throw new RuntimeException("Invalid log file format.");
            	}
            	
            	String sData = parts[0];
            	
            	String ip = parts[1];
            	
            	String request = parts[2];
            	
            	String sStatus = parts[3];
            	
            	String userAgent = parts[4];
            	
            	System.out.println(sData);
            	System.out.println(ip);
            	System.out.println(request);
            	System.out.println(sStatus);
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
	
}
