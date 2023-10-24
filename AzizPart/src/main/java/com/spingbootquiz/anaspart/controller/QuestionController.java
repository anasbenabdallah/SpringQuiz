package com.spingbootquiz.anaspart.controller;

import com.spingbootquiz.anaspart.models.Question;
import com.spingbootquiz.anaspart.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService; // You will need to create this service

    @PostMapping
    public Question createQuestion(@RequestBody Question question, @RequestParam Long quizId) {
        return questionService.createQuestion(question, quizId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        System.out.println("Received request for question with ID: " + id);
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            System.out.println("Question not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(question);
    }
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(questions);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        Question question = questionService.updateQuestion(id, updatedQuestion);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        boolean deleted = questionService.deleteQuestion(id);
        if (deleted) {
            String successMessage = "Question with ID " + id + " was deleted successfully.";
            return ResponseEntity.ok(successMessage);
        }
        return ResponseEntity.notFound().build();
    }


}
