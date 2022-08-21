package com.example.coursework_2.service.impl;

import com.example.coursework_2.exception.IncorrectAmountOfQuestionsException;
import com.example.coursework_2.model.Question;
import com.example.coursework_2.service.ExaminerService;
import com.example.coursework_2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 0 || amount > questionService.getAll().size()) {
            throw new IncorrectAmountOfQuestionsException();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }

}


//6. Реализовать ExaminerServiceImpl, который является реализацией интерфейса из прошлого шага. Данный сервис должен внутри себя хранить поля типа QuestionService.
//        Его задача: создать коллекцию и заполнить её с помощью вызова getRandomQuestion у QuestionService случайными вопросами.
//        Учтите:
//        1.  Вопросы должны быть уникальные, следовательно, для получения 5 вопросов может потребоваться более 5 вызовов метода getRandomQuestion сервиса вопросов.
//        2. Если запрошено большее количество вопросов, чем хранится в сервисе, нужно выкинуть исключение. Для этого, соответственно, нужно написать свое исключение со статусом BAD_REQUEST.