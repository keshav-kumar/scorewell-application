package com.scorewell.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scorewell.dto.QuestionSet;
import com.scorewell.dto.UserActivity;
import com.scorewell.login.service.UserService;
import com.scorewell.model.User;
import com.scorewell.service.DaoService;
import com.scorewell.service.ScorewellService;

@Controller
public class ScoreWellController {

	private final Logger logger = LoggerFactory.getLogger(ScoreWellController.class);
	
	@Autowired private Environment env;
	@Autowired private ScorewellService scorewellService;
	@Autowired private DaoService daoService;

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView homePageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome Scorewell Home page.");
		model.addAttribute("title", "An Institute for Civil Services Examination");
				
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(auth.getName());
//        System.out.println("USER NAme : "+user.getUserName());
        
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = { "/about" })
	public ModelAndView aboutPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to About Section");
		model.addAttribute("isDataAvail", "YES");
		
		return new ModelAndView("about-us");
	}
	
	@RequestMapping(value = { "/course-info" })
	public ModelAndView iasProgramPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to About Section");
		model.addAttribute("isDataAvail", "YES");
		
		return new ModelAndView("course-info");
	}
	
	@RequestMapping(value = { "/career" })
	public ModelAndView careerPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to About Section");
		model.addAttribute("isDataAvail", "YES");
		
		return new ModelAndView("career");
	}
	
	@RequestMapping(value = { "/dash" })
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to About Section");
		model.addAttribute("isDataAvail", "YES");
		
		return new ModelAndView("user/user-panel");
	}
	
	@RequestMapping(value = { "/daily-questions" })
	public ModelAndView iasDailyQuestionPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			logger.info("Welcome {} to Daily question Section",username);
		}else {
			  String username = principal.toString();
			  logger.info("Welcome {} to Daily question Section",username);
		}

		List<QuestionSet> questionSets = daoService.getQuestionSet(request);
		
		model.addAttribute("questionSets", questionSets);
		
		return new ModelAndView("daily-questions");
	}
	
	@RequestMapping(value = { "/question-set" })
	public ModelAndView questionSetPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(request.getParameter("set-name"));
		
		QuestionSet questionSet = scorewellService.getQuestionSetByName(request.getParameter("set-name"));
		model.addAttribute("questionSet", questionSet);
		model.addAttribute("fileBasePath", env.getProperty("resources.dir")+"question/");
		
		return new ModelAndView("question-set");
	}

	@RequestMapping(value = { "/ias-courses" })
	public ModelAndView iasCoursePageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to IAS Question Section");
		model.addAttribute("title", "An Institute for Civil Services Examination");
		return new ModelAndView("ias-daily-papers");
	}
	
	@RequestMapping(value = { "/set-list" })
	public ModelAndView setListPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<UserActivity> activitySetList = daoService.getUserActivity(request);
		model.addAttribute("sets", activitySetList);
		return new ModelAndView("setList");
	}
	
	@RequestMapping(value = "/reviewcomment", method = { RequestMethod.GET }, consumes = { "text/plain", "application/*" })
	public ModelAndView reviewWithCommentController(@RequestParam String name, @RequestParam String phone, @RequestParam String email, @RequestParam String setName, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		UserActivity activity = daoService.getUserActivity(name, phone, email, setName);
		QuestionSet questionSet = daoService.getQuestionSetByName(setName);
		model.addAttribute("activityDetails", activity);
		model.addAttribute("questionFile", questionSet);
		
		System.out.println("User Name : "+name);
		
		return new ModelAndView("reviewWithComment");
	}
	
	@RequestMapping(value = { "/courses" })
	public ModelAndView coursePageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to News Section");
		model.addAttribute("isDataAvail", "YES");
		
		return new ModelAndView("courses");
	}
	
	@RequestMapping(value = { "/contact" })
	public ModelAndView contactPageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to CONTACT Section");
		
		return new ModelAndView("contact");
	}
	
	@RequestMapping(value = { "/resource" })
	public ModelAndView resourcePageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to RESOURCE Section");
		
		return new ModelAndView("resource");
	}
	
	@RequestMapping(value = { "/jpsc-home" })
	public ModelAndView jpscHomePageController(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Welcome to Jpsc Home Page");
		model.addAttribute("title", "JPSC Home Page");
		
		return new ModelAndView("jpsc-home");
	}
	
}
