package com.scorewell.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class FileDownloadController {

	@Autowired private Environment env;
	private static final String EXTERNAL_FILE_PATH = "/Users/keshavkumar/Desktop";

	@RequestMapping("/question/file/{fileName:.+}")
	public void downloadQuestionResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		File file = new File(env.getProperty("resources.dir")+ "question/"+ fileName);
		fileDownloadOperation(response, file);
//		if (file.exists()) {
//
//			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//			if (mimeType == null) {
//				mimeType = "application/octet-stream";
//			}
//			response.setContentType(mimeType);
//			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
//
//			 //Here we have mentioned it to show as attachment
//			// response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
//
//			response.setContentLength((int) file.length());
//			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//			FileCopyUtils.copy(inputStream, response.getOutputStream());
//		}
	}
	
	@RequestMapping("/answer/file/{fileName:.+}")
	public void downloadAnsweresource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		System.out.println("Download Answer file : "+fileName);
		File file = new File(env.getProperty("resources.dir")+ "answer/"+ fileName);
		fileDownloadOperation(response, file);
	}
	
	@RequestMapping("/reviewed/file/{fileName:.+}")
	public void downloadReviewResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		File file = new File(env.getProperty("resources.dir")+ "reviewed/"+ fileName);
		fileDownloadOperation(response, file);
	}
	
	private void fileDownloadOperation(HttpServletResponse response, File file) throws IOException{
		if (file.exists()) {

			System.out.println("Full File Path : "+file.getAbsolutePath());
			
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			 //Here we have mentioned it to show as attachment
			// response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
}
