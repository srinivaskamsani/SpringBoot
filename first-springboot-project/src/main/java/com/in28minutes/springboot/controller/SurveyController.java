package com.in28minutes.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;

@RestController
public class SurveyController {

@Autowired
private SurveyService surveryService;

@GetMapping("/surveys/{surveyId}/questions")
public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId){
	return surveryService.retrieveQuestions(surveyId);
}

@PostMapping("/surveys/{surveyId}/questions")
public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion){
	
	Question question = surveryService.addQuestion(surveyId, newQuestion);
	if (question == null) {
		ResponseEntity.noContent().build();	
	}
	
	URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri();
	
	return ResponseEntity.created(location).build();
	
} 

@GetMapping("/surveys/{surveyId}/questions/{questionId}")
public Question retrieveDetailsForQuestion(@PathVariable String surveyId,@PathVariable String questionId){
	return surveryService.retrieveQuestion(surveyId, questionId) ;
} 


}
