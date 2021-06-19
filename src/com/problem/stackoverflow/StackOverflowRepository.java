package com.problem.stackoverflow;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class StackOverflowRepository {
    private AtomicLong memberId;
    private AtomicLong questionId;
    private AtomicLong answerId;
    private Map<Long, Member> members;
    private Map<Long, Question> questions;
    private Map<Long, Answer> answers;
    private Map<String, List<Long>> searchQuestions; // reverseIndex for question search

    StackOverflowRepository() {
        memberId = new AtomicLong(0);
        questionId = new AtomicLong(0);
        members = new HashMap<>();
        questions = new HashMap<>();
    }

    public Question addQuestion(long memberId, Question question){
        question.setId(questionId.incrementAndGet());
        questions.put(questionId.get(), question);
        members.get(memberId).addQuestionId(questionId.get());
        createReverseIndex(question);
        return question;
    }

    public long addMember(Member member) {
        member.setId(memberId.incrementAndGet());
        members.put(memberId.get(), member);
        return memberId.get();
    }

    public Member getMember(long memberId){
        return members.get(memberId);
    }

    public List<Question> searchQuestion(String search){
        String[] words = search.split("//s");
        List<Long> questionIds = new ArrayList<>();
        for(String word: words){
            questionIds.addAll(searchQuestions.get(word));
        }
        return questionIds.stream().distinct().map(id -> questions.get(id)).collect(Collectors.toList());
    }

    public long addAnswer(Answer answer,  long memberId, long questionId){
        answer.setId(answerId.incrementAndGet());
        answers.put(answerId.get(), answer);
        members.get(memberId).addAnswerId(answerId.get());
        questions.get(memberId).addAnswerId(answerId.get());
        return  answer.getId();
    }

    private void createReverseIndex(Question question){
        String[] words = question.getDescription().split("//s");
        for(String word: words){
            List<Long> ids = searchQuestions.getOrDefault(word, new ArrayList<>());
            ids.add(question.getId());
            searchQuestions.put(word, ids);
        }

    }

}
