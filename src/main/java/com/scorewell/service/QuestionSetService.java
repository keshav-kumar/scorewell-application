package com.scorewell.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.scorewell.service.DaoService;
import com.scorewell.utils.JsonUtils;
import com.scorewell.utils.StringUtils;
import com.google.gson.JsonObject;
import com.scorewell.dto.Question;
import com.scorewell.dto.QuestionSet;
import com.scorewell.dto.UserActivity;
import com.scorewell.model.User;

@Service
public class QuestionSetService {

	private static Logger logger = LoggerFactory.getLogger(QuestionSetService.class);

	@Autowired
	private DaoService daoService;

	public String createQuestionSet(HttpServletRequest request) {//, String pdfFileName) {

		QuestionSet questionSet = new QuestionSet();

		questionSet.setCourse(request.getParameter("course"));
//		questionSet.setSetName(request.getParameter("setName"));
		questionSet.setSubjectName(request.getParameter("subject"));

		questionSet.setReleaseDate(StringUtils.strToDate(request.getParameter("publishDate"), "dd-MM-yyyy").getTime()+(3 * 70 * 60 * 1000));

		String setName = request.getParameter("subject") + "_Test_" + StringUtils.generateSetNumber(
				daoService.getLatestQuestionSetName(request.getParameter("course"), request.getParameter("subject")));
		questionSet.setSetName(setName);

		List<Question> questions = new ArrayList<Question>();
		int totalQue = Integer.parseInt(request.getParameter("TotalQuestions"));
		for(int i =1; i< totalQue; i++) {
			
			Question question = new Question();
			question.setQuestion(request.getParameter("que"+i));
			question.setQuestionId(request.getParameter("course") + "_" + setName + "-Q"+i);
			questions.add(question);
			
			System.out.println("Yoo Que"+i+" : "+request.getParameter("que"+i));
		}
		
//		Question question1 = new Question();
//		question1.setQuestion(request.getParameter("que1"));
//		question1.setQuestionId(request.getParameter("course") + "_" + setName + "-Q1");
//		questions.add(question1);
		
		questionSet.setQuestions(questions);
//		questionSet.setPdfFileName(pdfFileName);

		questionSet.setCreateTime(System.currentTimeMillis());
		questionSet.setUpdateTime(System.currentTimeMillis());
		questionSet.setDeleted(false);

		return daoService.createQuestionSet(questionSet);
	}
	
	public String saveUserActivity(HttpServletRequest request, String filePath, User user) {
		
		UserActivity userActivity = new UserActivity();
		userActivity.setUserName(user.getName());
		userActivity.setEmailId(user.getEmail());
		userActivity.setPhone("");
		userActivity.setCourse(request.getParameter("courseName"));
		userActivity.setSubjectName(request.getParameter("subjectName"));
		userActivity.setSetName(request.getParameter("setName"));
		userActivity.setFileName(filePath.substring(filePath.lastIndexOf("\\")+1));
		userActivity.setFilePath(filePath);
		userActivity.setReviwedUploaded(false);
		userActivity.setEvaluated(false);
		userActivity.setUploadDateTime(System.currentTimeMillis());
		userActivity.setEvaluateDateTime(System.currentTimeMillis());
		
		String userActivityId = daoService.saveUserActivity(userActivity);
		
		return userActivityId;
	}
	
	public JsonObject deleteAdditionInsured(String setName) {
		daoService.deleteQuestionSet(setName);
		return JsonUtils.createSuccessResponse("deleted " + setName);
	}
	
	
	
	public void updateUserActivity(HttpServletRequest request, boolean reviewUpload) {
		
		daoService.updateUserActivity(request, reviewUpload);
		
	}

}
