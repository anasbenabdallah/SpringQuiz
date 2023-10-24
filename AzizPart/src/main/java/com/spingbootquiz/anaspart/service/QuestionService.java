package com.spingbootquiz.anaspart.service;

import com.spingbootquiz.anaspart.models.Question;
import com.spingbootquiz.anaspart.models.Quiz;
import com.spingbootquiz.anaspart.repository.QuestionRepository;
import com.spingbootquiz.anaspart.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository; // You will need to create this repository
    @Autowired
    private QuizRepository quizRepository;

    public Question createQuestion(Question question, Long quizId) {
        // Fetch the quiz from the database based on the provided quizId
        Quiz quiz = quizRepository.findById(quizId).orElse(null);

        if (quiz != null) {
            // Associate the question with the quiz
            question.setQuiz(quiz);
            // Save the question to the database
            return questionRepository.save(question);
        } else {
            // Handle the case where the provided quizId is invalid
            // You may want to return an error response or handle it as needed
            return null;
        }
    }
    // Get a question by its ID
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    // Update a question by its ID
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);
        if (existingQuestion != null) {
            // Update the existing question with the new data
            existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            existingQuestion.setOption1(updatedQuestion.getOption1());
            existingQuestion.setOption2(updatedQuestion.getOption2());
            existingQuestion.setOption3(updatedQuestion.getOption3());
            existingQuestion.setOption4(updatedQuestion.getOption4());
            existingQuestion.setCorrectOption(updatedQuestion.getCorrectOption());
            existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            existingQuestion.setCategory(updatedQuestion.getCategory());

            return questionRepository.save(existingQuestion);
        }
        return null; // Question not found
    }

    // Delete a question by its ID
    public boolean deleteQuestion(Long id) {
        Question existingQuestion = questionRepository.findById(id).orElse(null);
        if (existingQuestion != null) {
            questionRepository.delete(existingQuestion);
            return true; // Question deleted successfully
        }
        return false; // Question not found
    }
}
