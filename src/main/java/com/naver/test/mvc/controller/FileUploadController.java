package com.naver.test.mvc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mvc")
public class FileUploadController {
	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping("/uploadForm")
	public String form() {
		return "fileUpload";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
			throws IOException {

		String path = "D:/picture/";

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + name)));
			stream.write(bytes);
			stream.close();

			logger.info("file name : {}", file.getOriginalFilename());
			logger.info("file size : {}", file.getSize());
			logger.info("file content type : {}", file.getContentType());

			return "You successfully uploaded " + name + "!";
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	};
}
