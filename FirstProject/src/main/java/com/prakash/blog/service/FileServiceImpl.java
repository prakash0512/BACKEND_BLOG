package com.prakash.blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServiceImpl implements FileService {

	
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
	    String originalFileName = file.getOriginalFilename();
	    
	    System.out.println("Uploading file: " + originalFileName);
	    String randomID = UUID.randomUUID().toString();
	    String fileName = randomID.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));

	    String filePath = path + File.separator + fileName;

	    File directory = new File(path);
	    if (!directory.exists()) {
	        directory.mkdir();  
	    }

	    Files.copy(file.getInputStream(), Paths.get(filePath));  
	    
	    System.out.println("File uploaded to: " + filePath);

	    return fileName;  
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

}

