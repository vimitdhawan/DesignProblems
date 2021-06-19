package com.problem.stackoverflow;

import java.util.*;
public class Question {
    private Long id;
    private String title;
    private String description;
    private List<Long> answerIds;
    Question(String title, String description){
        this.title = title;
        this.description = description;
    }
    public void setId(Long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void addAnswerId(long id) {
        this.answerIds.add(id);
    }

    public String getDescription() {
        return this.description;
    }
}
