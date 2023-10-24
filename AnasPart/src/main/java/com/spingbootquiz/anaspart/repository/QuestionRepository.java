package com.spingbootquiz.anaspart.repository;

import com.spingbootquiz.anaspart.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionRepository extends JpaRepository<Question, Long> {


    }
