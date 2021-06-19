package com.problem.stackoverflow;

import java.util.List;

public class Member {
    private long id;
    private String memberName;
    private List<Long> questionIds;
    private List<Long> answerIds;
    Member(String memberName){
        this.memberName = memberName;
    }

    public long createQuestion(StackOverflowRepository stackOverflowRepository, String title, String description){
        Question question = new Question(title, description);
        stackOverflowRepository.addQuestion(this.id,question);
        return question.getId();
    }

    public long createAnswer(StackOverflowRepository stackOverflowRepository, String description, long questionId){
        Answer answer = new Answer(description);
        return stackOverflowRepository.addAnswer(answer, this.id, questionId);
    }


    public void addQuestionId(long id){
        this.questionIds.add(id);
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void addAnswerId(long id) {
        answerIds.add(id);
    }
}
