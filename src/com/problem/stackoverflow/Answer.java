package com.problem.stackoverflow;

import java.util.List;

public class Answer {
    private Long id;
    private String description;

     Answer(String description) {
         this.description = description;
    }

    public void setId(Long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }
}
