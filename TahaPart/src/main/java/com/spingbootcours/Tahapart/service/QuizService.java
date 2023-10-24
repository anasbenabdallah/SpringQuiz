package com.spingbootcours.Tahapart.service;

import com.spingbootcours.Tahapart.models.Employee;
import com.spingbootcours.Tahapart.models.Quiz;
import com.spingbootcours.Tahapart.repository.EmployeeRepo;
import com.spingbootcours.Tahapart.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository; // You will need to create this repository
    @Autowired
    private EmployeeRepo employeeRepo;
    public Quiz createQuiz(Quiz quiz) {
        // You can perform any necessary validation or business logic here
        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }

    public List<Quiz> getAllQuizzesWithQuestions() {
        return quizRepository.findAll();
    }
    public void assignQuizToEmployee(String email, Long quizId) {
        Employee employee = employeeRepo.findByEmail(email);
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found!"));

        employee.getQuizzes().add(quiz);
        employeeRepo.save(employee);
    }


}
