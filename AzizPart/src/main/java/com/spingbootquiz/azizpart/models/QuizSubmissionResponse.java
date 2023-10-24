package com.spingbootquiz.azizpart.models;

import jakarta.persistence.*;


@Entity
public class QuizSubmissionResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String quizTitle;
    private double score;
    private String email;

   public QuizSubmissionResponse() {
    }

    public QuizSubmissionResponse(String userName, String quizTitle, double score,String email) {
        this.userName = userName;
        this.quizTitle = quizTitle;
        this.score = score;
        this.email = email;
    }


    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
