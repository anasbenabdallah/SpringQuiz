package com.spingbootquiz.azizpart.service;

import com.spingbootquiz.azizpart.models.QuizSubmissionResponse;
import com.spingbootquiz.azizpart.repository.QuizSubmissionResponseRepository;
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
