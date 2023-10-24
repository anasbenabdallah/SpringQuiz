package com.spingbootcours.Tahapart.service;

import com.spingbootcours.Tahapart.models.QuizSubmissionResponse;
import com.spingbootcours.Tahapart.repository.QuizSubmissionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizSubmissionResponseService {

    @Autowired
    private QuizSubmissionResponseRepository quizSubmissionResponseRepository;

    public List<QuizSubmissionResponse> getAllQuizSubmissions() {
        return quizSubmissionResponseRepository.findAll();
    }
}
