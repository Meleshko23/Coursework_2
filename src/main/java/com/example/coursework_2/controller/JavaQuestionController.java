package com.example.coursework_2.controller;


import com.example.coursework_2.model.Question;
import com.example.coursework_2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(String question, String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(String question, String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }

}

//    Контроллер должен иметь три метода: добавить, удалить и получить все вопросы.
//
//        Эти методы должны висеть на следующих эндпоинтах:
//
//        Добавить: “/exam/java/add?question=QuestionText&answer=QuestionAnswer”
//
//        Удалить: “/exam/java/remove?question=QuestionText&answer=QuestionAnswer”
//
//        Получить все вопросы: “/exam/java”