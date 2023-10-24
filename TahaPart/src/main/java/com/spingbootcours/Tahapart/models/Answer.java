package com.spingbootcours.Tahapart.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @JsonProperty("userResponse")
    private int userResponse;

    // Constructors, getters, and setters

    // Constructors

    public Answer() {
        // Default constructor
    }

    public Answer(Question question, int userResponse) {
        this.question = question;
        this.userResponse = userResponse;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(int userResponse) {
        this.userResponse = userResponse;
    }
}
