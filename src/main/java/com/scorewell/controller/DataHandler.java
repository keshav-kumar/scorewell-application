package com.scorewell.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scorewell.login.service.UserService;
import com.scorewell.model.User;
import com.scorewell.service.DaoService;
import com.scorewell.service.QuestionSetService;
import com.scorewell.service.UploadService;


@RestController
@RequestMapping(("/sw/"))
public class DataHandler {

	private final Logger logger = LoggerFactory.getLogger(DataHandler.class);

	@Autowired
    private Environment env;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DaoService daoService;

	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private QuestionSetService questionSetService;

//	@RequestMapping(path = "/find")
//	@ResponseBody
//	public String search() {
//
////		List<String> list = daoService.getUrlsFromWebpageData();
//		List<QuestionSet> setName = daoService.getLatestQuestionSetName("IAS", "Agri");
//
//		return new Gson().toJson(setName);
//	}

	@RequestMapping(value = { "/save-subscriber" })
	public @ResponseBody String subscriberPageController(HttpServletRequest request, HttpServletResponse response, Model model) {

		String email = request.getParameter("userName");
		JsonObject result =  userService.createSubscribeOnlyUser(email);
		
		if(result.get("success").getAsBoolean()) {
			return "Welcome to Score Well";
		}else
			return result.get("error").toString();
	}
	
	@PostMapping("/api/create-question-set")
	public ResponseEntity<?> uploadQuestions(HttpServletRequest request, HttpServletResponse response){
//			,@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

//		if (uploadfile.isEmpty()) {
//			return new ResponseEntity("please select a file!", HttpStatus.BAD_REQUEST);
//		}
		String questionsetId = questionSetService.createQuestionSet(request);//, uploadfile.getOriginalFilename());
//		try {
//			uploadService.saveUploadedPdfFiles(Arrays.asList(uploadfile), request, "Q");
//
//		} catch (IOException e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}

		return new ResponseEntity("Question saved successfully.", new HttpHeaders(), HttpStatus.OK);

	}
	

	@RequestMapping(value = { "/api/delete-question-set" })
	public @ResponseBody String deleteQuestionSetController(@RequestParam String setname) {

		System.out.println("Deleting QueSet : "+setname);
		return questionSetService.deleteAdditionInsured(setname).toString();
	}

	@PostMapping("/api/upload-answer")
	public Map<String, Object> uploadAnswer(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile uploadfile, Principal principal, RedirectAttributes redirectAttributes) {
		Map<String, Object> res = new HashMap<>();
		if (uploadfile.isEmpty()) {
			res.put("error", "Please select a file!");
			return res;
		}
		if(checkMimeType(uploadfile.getContentType())) { 
			res.put("error", "Invalid File Format, Please Upload only (PDF, JPG, JPEG or PNG) Files");
			return res;
		}
		User user = userService.findUserByUserName(principal.getName());
		String savedFilePath = uploadService.saveUploadedPdfFiles(Arrays.asList(uploadfile), request, "A", user);
		questionSetService.saveUserActivity(request, savedFilePath, user);
		res.put("success", "Successfully uploaded - " + uploadfile.getOriginalFilename());
		return res;
	}
	
	@PostMapping("/api/reviewed-upload")
	public ResponseEntity<?> uploadReviewed(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile uploadfile, Principal principal) {

		logger.info("Reviewed answer uploading.");

		if (uploadfile.isEmpty() && request.getParameter("reviewComment").isEmpty()) {
			return new ResponseEntity("Please select a file or put some review comment !", HttpStatus.BAD_REQUEST);
		}
		User user = userService.findUserByUserName(principal.getName());
		questionSetService.updateUserActivity(request, !uploadfile.isEmpty());
		String savedFilePath = uploadService.saveUploadedPdfFiles(Arrays.asList(uploadfile), request, "R", user);

		return new ResponseEntity("Review saved Successfully", new HttpHeaders(), HttpStatus.OK);

	}
	
	private boolean checkMimeType(String contentType) {
		String[] allowedMimeType = {"image/png", "image/jpg", "image/jpeg", "application/pdf"};
        for (int i = 0; i < allowedMimeType.length; i++) {
        	if(allowedMimeType[i].equalsIgnoreCase(contentType)) {
        		return false;
        	}
		}
		return true;
	}
}
