package com.example.coursework_2;

import com.example.coursework_2.exception.IncorrectAmountOfQuestionsException;
import com.example.coursework_2.model.Question;
import com.example.coursework_2.service.QuestionService;
import com.example.coursework_2.service.impl.ExaminerServiceImpl;
import com.example.coursework_2.service.impl.JavaQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void getQuestionsNegativeTest() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());

        Assertions.assertThatExceptionOfType(IncorrectAmountOfQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(1));

        Assertions.assertThatExceptionOfType(IncorrectAmountOfQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    public void getQuestionsPositiveTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        Set<Question> allQuestions = Set.of(question1, question2, question3, question4, question5);

        when(javaQuestionService.getAll()).thenReturn(allQuestions);

        when(javaQuestionService.getRandomQuestion()).thenReturn(question5, question4, question1);
        assertThat(examinerService.getQuestions(3)).containsExactlyInAnyOrder(question5, question4, question1);

        when(javaQuestionService.getRandomQuestion()).thenReturn(question5, question4, question1, question5, question2, question4, question3);
        assertThat(examinerService.getQuestions(5)).containsExactlyInAnyOrder(question5, question4, question1, question2, question3);
    }
}


//    Покрыть юнит-тестами JavaQuestionService и ExaminerServiceImpl (потребуется мок).