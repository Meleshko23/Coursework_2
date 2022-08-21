package com.example.coursework_2.service;

import com.example.coursework_2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}

//    Сделать интерфейс ExaminerService с одним методом getQuestions.
//    Этот интерфейс должен содержать один метод, который вернет список вопросов.