package com.scorewell.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scorewell.dto.QuestionSet;

@Service
public class ScorewellService {

	private static Logger logger = LoggerFactory.getLogger(ScorewellService.class);
	
	@Autowired private DaoService daoService;
	
	
	public QuestionSet getQuestionSetByName(String setName) {

		QuestionSet questionSet = daoService.getQuestionSetByName(setName);
		
		return questionSet;
	}
	
}
