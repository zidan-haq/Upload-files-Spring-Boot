package com.haq.fileUpload;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.haq.fileUpload.controller.FileUploadController;

@SpringBootApplication
public class FileUploadApplication {

	public static void main(String[] args) {
		
		// Este comando vai criar a pasta uploads se ela não existir
		new File(FileUploadController.uploadDirectory).mkdir();
		
		SpringApplication.run(FileUploadApplication.class, args);
	}

}
