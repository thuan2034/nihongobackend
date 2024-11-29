package com.app.nihongo.controller;

import com.app.nihongo.dto.UserFailedQuestionDTO;
import com.app.nihongo.enums.QuestionType;
import com.app.nihongo.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{lessonId}")
    public ResponseEntity<?> getQuestionsByTypeAndLessonId(
            @RequestParam Integer userId,
            @RequestParam("type") QuestionType type,
            @PathVariable Integer lessonId) {
        return questionService.getQuestionsByTypeAndLessonId(userId, type, lessonId);
    }
    @GetMapping("/practice")
    public ResponseEntity<List<UserFailedQuestionDTO>> getFailedQuestions(@RequestParam Integer userId) {
        List<UserFailedQuestionDTO> failedQuestions = questionService.getFailedQuestionsByUserId(userId);
        return ResponseEntity.ok(failedQuestions);
    }
    @GetMapping("/{type}/{questionId}")
    public ResponseEntity<?> getQuestionContent(
            @PathVariable QuestionType type,
            @PathVariable Integer questionId) {

        return questionService.getQuestionContentByTypeAndId(type, questionId);
    }
}
