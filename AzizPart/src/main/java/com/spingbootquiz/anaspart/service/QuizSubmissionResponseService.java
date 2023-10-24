package com.spingbootquiz.anaspart.service;

import com.spingbootquiz.anaspart.models.QuizSubmissionResponse;
import com.spingbootquiz.anaspart.repository.QuizSubmissionResponseRepository;
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
