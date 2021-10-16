package com.haq.fileUpload.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	// Nessa pasta serão armazenados os arquivos do upload
	// user.dir retorna a pasta principal do programa "FileUpload"
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@GetMapping("/")
	public String uploadPage() {
		return "uploadview";
	}

	/*
	 * Esse método vai pegar cada um dos arquivos enviados pela requisição post e armazenálos na pasta informada
	 * pela variável uploadDirectory.
	 * Se o dado informado possuir o mesmo nome de um dado existente então o dado existente será sobrescrito.
	 * No arquivo de configuração "application.properties" na pasta "src/main/resouces" há configurações que
	 * especificam o tamanho máximo de upload e de requisição de arquivos.
	 */
	@PostMapping("/upload")
	public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + " ");
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("msg", "Successfully uploaded files " + fileNames.toString());
		return "uploadstatusview";
	}

}
