package com.spingbootquiz.azizpart.controller;

import com.spingbootquiz.azizpart.models.Quiz;
import com.spingbootquiz.azizpart.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes") // Define a base URL for your quiz API
public class QuizController {
    @Autowired
    private QuizService quizService; // You will need to create this service

    // Add a method to handle POST requests for creating quizzes
    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzesWithQuestions() {
        List<Quiz> quizzes = quizService.getAllQuizzesWithQuestions();

        if (quizzes != null && !quizzes.isEmpty()) {
            return ResponseEntity.ok(quizzes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
   @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId) {
        Quiz quiz = quizService.getQuizById(quizId);

        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
