package com.scorewell.controller;

import com.scorewell.model.UploadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RestUploadController {

	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

	@Autowired
    private Environment env;
	
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "/Users/keshavkumar/Downloads/";

	@PostMapping("/upload/mcq")
	public ResponseEntity<?> uploadCsvFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile csvFile){
		
		logger.debug("Upload multiple choice question csv...!");

		if (csvFile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity("Successfully uploaded - " + csvFile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);
	}
	
	
	@PostMapping("/api/upload")	//	******************************************************************************
	// If not @RestController, uncomment this
	// @ResponseBody
	public ResponseEntity<?> uploadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
//			return new ResponseEntity("please select a file!", HttpStatus.OK);
			return new ResponseEntity("please select a file!", HttpStatus.BAD_REQUEST);
		}

		try {

			saveUploadedFiles(Arrays.asList(uploadfile), request.getParameter("phone"));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}

//	// Multiple file upload
//	@PostMapping("/api/upload/multi")
//	public ResponseEntity<?> uploadFileMulti(@RequestParam("extraField") String extraField,
//			@RequestParam("files") MultipartFile[] uploadfiles) {
//		logger.debug("Multiple file upload!");
//		System.out.println("Called for upload multi");
//		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
//				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
//
//		if (StringUtils.isEmpty(uploadedFileName)) {
//			return new ResponseEntity("please select a file!", HttpStatus.OK);
//		}
//		try {
//			saveUploadedFiles(Arrays.asList(uploadfiles), );
//
//		} catch (IOException e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);
//	}

//	// maps html form to a Model
//	@PostMapping("/api/upload/multi/model")
//	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {
//		logger.debug("Multiple file upload! With UploadModel");
//		try {
//			saveUploadedFiles(Arrays.asList(model.getFiles()), );
//
//		} catch (IOException e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
//	}

	// save file
	private void saveUploadedFiles(List<MultipartFile> files, String phoneNo) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(env.getProperty("upload.question.dir") + phoneNo+"_"+file.getOriginalFilename());
			Files.write(path, bytes);

		}

	}
}
