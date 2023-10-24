package com.spingbootquiz.azizpart.models;

import jakarta.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private int correctOption; // Store the index (1, 2, 3, or 4) of the correct option

    private String difficultyLevel; // New attribute: Difficulty Level
    private String category; // New attribute: Category

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // Constructors, getters, and setters

    // Constructors

    public Question() {
        // Default constructor
    }

    public Question(String questionTitle, String option1, String option2, String option3, String option4, int correctOption, String difficultyLevel, String category, Quiz quiz) {
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.difficultyLevel = difficultyLevel;
        this.category = category;
        this.quiz = quiz;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
