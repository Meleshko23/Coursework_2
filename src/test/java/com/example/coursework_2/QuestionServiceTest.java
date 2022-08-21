package com.example.coursework_2;

import com.example.coursework_2.exception.QuestionAlreadyExistException;
import com.example.coursework_2.exception.QuestionNotFoundException;
import com.example.coursework_2.model.Question;
import com.example.coursework_2.service.impl.JavaQuestionService;
import com.example.coursework_2.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addRemoveTest() {
        Question question = new Question("question", "answer");

        // case 1 (add)
        questionService.add(question);
        assertThat(questionService.getAll()).contains(question);

        // case 2 (negative add)
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add("question", "answer"));

        // case 3 (remove)
        questionService.remove(question);
        assertThat(questionService.getAll()).isEmpty();

        // case 4 (negative remove)
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question));
    }

    @Test
    public void getRandomQuestionTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        questionService.add(question1);
        questionService.add(question2);
        questionService.add(question3);

        assertThat(questionService.getAll()).contains(questionService.getRandomQuestion());
    }

}

//    Покрыть юнит-тестами JavaQuestionService и ExaminerServiceImpl (потребуется мок).