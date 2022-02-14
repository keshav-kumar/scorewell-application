package com.scorewell.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.scorewell.controller.DataHandler;

@Service
public class UploadService {
	private final Logger logger = LoggerFactory.getLogger(UploadService.class);

	@Autowired
	private Environment env;

	public String saveUploadedPdfFiles(List<MultipartFile> files, HttpServletRequest request, String uploadType) {

		String filePath = "";
		for (MultipartFile file : files) {

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					String subDir_fileName = "";
//				if (uploadType.equals("Q"))
//					subDir_fileName = env.getProperty("upload.question.dir") + file.getOriginalFilename();
//				else 
					if (uploadType.equals("A")) {
						subDir_fileName = env.getProperty("upload.answer.dir") + request.getParameter("phone") + "_"
								+ request.getParameter("email") + "_" + request.getParameter("setName") + ".pdf";
						logger.info("Saved the answersheet {}, uploaded by {} of Set {}", subDir_fileName,
								request.getParameter("email"),request.getParameter("setName"));
					}
					else if (uploadType.equals("R")) {
						subDir_fileName = env.getProperty("upload.reviewed.dir") + request.getParameter("fileName");
						logger.info("Saved the answer Reviewed sheet {}", subDir_fileName);
					}

					
					System.out.println("Sub DIR : " + subDir_fileName);
					Path path = Paths.get(env.getProperty("resources.dir") + subDir_fileName);
					Files.write(path, bytes);

					filePath = path.toAbsolutePath().toString();
					System.out.println("ABS PATH : " + filePath);

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			} else {
				continue;
			}
		}

		return filePath;
	}
}
