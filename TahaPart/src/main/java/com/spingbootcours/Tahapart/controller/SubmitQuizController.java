package com.spingbootcours.Tahapart.controller;

import com.spingbootcours.Tahapart.models.*;
import com.spingbootcours.Tahapart.models.*;
import com.spingbootcours.Tahapart.repository.QuizSubmissionResponseRepository;
import com.spingbootcours.Tahapart.service.EmployeeService;
import com.spingbootcours.Tahapart.service.QuizService;
import com.spingbootcours.Tahapart.service.QuizSubmissionResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")

public class SubmitQuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private QuizSubmissionResponseService quizSubmissionResponseService;

    @Autowired
    private QuizSubmissionResponseRepository quizSubmissionResponseRepository;


    @PostMapping("/submit/{employeeId}/{quizId}")
    public ResponseEntity<QuizSubmissionResponse> submitQuiz(
            @PathVariable Long employeeId,
            @PathVariable Long quizId,
            @RequestBody List<Answer> answers) {
        if (answers == null || answers.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            // Handle employee not found
            return ResponseEntity.notFound().build();
        }

        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) {
            // Handle quiz not found
            return ResponseEntity.notFound().build();
        }

        List<Question> questions = quiz.getQuestions();
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (Answer answer : answers) {
            Question question = answer.getQuestion();
            int userResponse = answer.getUserResponse();

            if (question != null) {
                int correctOption = question.getCorrectOption();

                if (correctOption == userResponse) {
                    correctAnswers++;
                }
            }
        }

        double score = (double) correctAnswers / totalQuestions * 100;

        // Create a response object with user info, quiz title, and score
        QuizSubmissionResponse response = new QuizSubmissionResponse(
                employee.getEmployeename(),
                quiz.getTitle(),
                score,
                employee.getEmail());
        quizSubmissionResponseRepository.save(response);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/submissions")
    public ResponseEntity<List<QuizSubmissionResponse>> getAllSubmissions() {
        List<QuizSubmissionResponse> submissions = quizSubmissionResponseService.getAllQuizSubmissions();
        return ResponseEntity.ok(submissions);
    }
}
