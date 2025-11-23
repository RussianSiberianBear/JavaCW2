package org.skypro.coursework2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework2.exception.MathOperationNotAllowedException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.repository.CommonQuestionRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest2 {

    @Mock
    private CommonQuestionRepository repository;

    @Test
    void add_ShouldThrowMathOperationNotAllowedException() {
        MathQuestionService mathService = new MathQuestionService(repository);

        assertThrows(MathOperationNotAllowedException.class,
                () -> mathService.add("What is 2+2?", "4"));
    }

    @Test
    void remove_ShouldThrowMathOperationNotAllowedException() {
        MathQuestionService mathService = new MathQuestionService(repository);
        Question question = new Question("What is 2+2?", "4");

        assertThrows(MathOperationNotAllowedException.class,
                () -> mathService.remove(question));
    }

    @Test
    void getAll_ShouldThrowMathOperationNotAllowedException() {
        MathQuestionService mathService = new MathQuestionService(repository);

        assertThrows(MathOperationNotAllowedException.class,
                mathService::getAll);
    }

    @Test
    void getRandomQuestion_ShouldThrowMathOperationNotAllowedException() {
        MathQuestionService mathService = new MathQuestionService(repository);

        assertThrows(MathOperationNotAllowedException.class,
                mathService::getRandomQuestion);
    }

    @Test
    void constructor_ShouldInitializeService() {
        MathQuestionService mathService = new MathQuestionService(repository);
        assertNotNull(mathService);
    }

    @Test
    void allMethods_ShouldThrowMathOperationNotAllowedException_WhenAnyOperationCalled() {
        MathQuestionService mathService = new MathQuestionService(repository);
        Question testQuestion = new Question("Test", "Answer");

        assertThrows(MathOperationNotAllowedException.class,
                () -> mathService.add("Question", "Answer"));

        assertThrows(MathOperationNotAllowedException.class,
                () -> mathService.remove(testQuestion));

        assertThrows(MathOperationNotAllowedException.class,
                mathService::getAll);

        assertThrows(MathOperationNotAllowedException.class,
                mathService::getRandomQuestion);
    }
}