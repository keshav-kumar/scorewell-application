package com.scorewell.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scorewell.dto.QuestionSet;
import com.scorewell.dto.UserActivity;
import com.scorewell.login.service.UserService;
import com.scorewell.service.DaoService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired private DaoService daoService;
	@Autowired private UserService userService;

	@RequestMapping(value = { "/subscribers" })
	public ModelAndView getSubscribersPageController(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<String> subscribers = userService.getAllSubscribers();
		model.addAttribute("subscriber_email", subscribers);

		return new ModelAndView("subscribers");
	}
	
	@RequestMapping("/panel")
	public ModelAndView adminPageController(HttpServletRequest request, HttpServletResponse response,
			Model model) {

		List<QuestionSet> questionSets = daoService.getQuestionSet(request);
		model.addAttribute("questionSets", questionSets);
		
		return new ModelAndView("admin-questions");
	}

	@RequestMapping(value = { "/create-question" })
	public ModelAndView createQuestionPageController(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return new ModelAndView("create_question");
	}
	
	@RequestMapping(value = { "/admin-answer-list" })
	public ModelAndView answerSheetPageController(HttpServletRequest request, HttpServletResponse response,
			Model model) {

		List<UserActivity> listAnswerSheet = daoService.getUserActivityAdmin(request);
		model.addAttribute("answerSheet", listAnswerSheet);
		
		return new ModelAndView("admin-answer-page");
	}
	
	@RequestMapping(value = { "/upload-review" })
	public ModelAndView uploadReviewPageController(HttpServletRequest request, HttpServletResponse response,
			Model model) {

//		List<UserActivity> listAnswerSheet = daoService.getUserActivityAdmin(request);
		
		UserActivity listAnswerSheet = new UserActivity();
		listAnswerSheet.setSetName(request.getParameter("setName"));
		listAnswerSheet.setPhone(request.getParameter("phone"));
		listAnswerSheet.setEmailId(request.getParameter("emailId"));
		listAnswerSheet.setUserName(request.getParameter("userName"));
		listAnswerSheet.setCourse(request.getParameter("courseName"));
		listAnswerSheet.setSubjectName(request.getParameter("subjectName"));
		listAnswerSheet.setFileName(request.getParameter("fileName"));
		
		model.addAttribute("userAnswerSheet", listAnswerSheet);
		
		return new ModelAndView("upload-review-page");
	}

}
