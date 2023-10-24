package com.spingbootquiz.azizpart.repository;

import com.spingbootquiz.azizpart.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
