package com.spingbootquiz.anaspart.repository;

import com.spingbootquiz.anaspart.models.QuizSubmissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizSubmissionResponseRepository extends JpaRepository<QuizSubmissionResponse, Long> {

}
