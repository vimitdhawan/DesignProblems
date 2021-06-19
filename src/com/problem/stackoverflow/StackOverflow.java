package com.problem.stackoverflow;

import java.util.List;

public class StackOverflow {
    public static void main(String[] args) {
        StackOverflow app= new StackOverflow();
        StackOverflowRepository repository = new StackOverflowRepository();
        String memberName = "Vimit"; // assume this as a input  from application
        long memberId = app.registerMember(memberName, repository);
        long questionId = app.createQuestion(repository,memberId, "OO design stackoverflow", "How to do  OO design for stack overflow questions"); // when anymmeber will call we have only member id
        app.createAnswer(repository, "I dont know about OO design", questionId, memberId); // whe  someone call this function, it will be meber and on some squestion so we always have member Id
        app.searchQuestions( "ood", repository);
    }
    public long registerMember(String memberName, StackOverflowRepository repository){
        Member member = new Member(memberName);
        repository.addMember(member);
        return member.getId();
    }
    public long createQuestion(StackOverflowRepository repository, long memberId, String title, String description){
        Member member = repository.getMember(memberId);
        return member.createQuestion(repository, title, description);
    }

    public long createAnswer(StackOverflowRepository repository, String description, long questionId, long memberId){
        Member member = repository.getMember(memberId);
        return member.createAnswer(repository, description,questionId);
    }

    public List<Question> searchQuestions(String searchText, StackOverflowRepository repository){
        return  repository.searchQuestion(searchText);
    }
}
