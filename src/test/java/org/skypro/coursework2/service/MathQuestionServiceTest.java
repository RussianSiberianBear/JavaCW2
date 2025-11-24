package org.skypro.coursework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework2.exception.*;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private CommonQuestionRepository repository;

    private MathQuestionService mathQuestionService;

    private Question testQuestion;
    private Collection<Question> testQuestions;

    @BeforeEach
    void setUp() {
        mathQuestionService = new MathQuestionService(repository);
        testQuestion = new Question("What is 2+2?", "4");
        testQuestions = Set.of(
                testQuestion,
                new Question("What is 5*5?", "25"),
                new Question("What is 10/2?", "5"),
                new Question("What is 3-1?", "2")
        );
    }

    @Test
    void constructor_ShouldInitializeWithMathType() {
        Question result = new Question("test", "answer");
        when(repository.add(eq(QuestionType.MATH), eq("test"), eq("answer")))
                .thenReturn(result);

        mathQuestionService.add("test", "answer");

        verify(repository).add(QuestionType.MATH, "test", "answer");
    }

    @Test
    void add_ShouldReturnQuestion_WhenRepositorySucceeds() {
        String question = "What is 7*8?";
        String answer = "56";
        Question expectedQuestion = new Question(question, answer);

        when(repository.add(eq(QuestionType.MATH), eq(question), eq(answer)))
                .thenReturn(expectedQuestion);

        Question result = mathQuestionService.add(question, answer);

        assertEquals(expectedQuestion, result);
        verify(repository).add(QuestionType.MATH, question, answer);
    }

    @Test
    void remove_ShouldReturnRemovedQuestion_WhenRepositorySucceeds() {
        when(repository.remove(eq(QuestionType.MATH), eq(testQuestion)))
                .thenReturn(testQuestion);

        Question result = mathQuestionService.remove(testQuestion);

        assertEquals(testQuestion, result);
        verify(repository).remove(QuestionType.MATH, testQuestion);
    }

    @Test
    void getAll_ShouldReturnQuestionCollection_WhenRepositorySucceeds() {
        when(repository.getAll(eq(QuestionType.MATH)))
                .thenReturn(testQuestions);

        Collection<Question> result = mathQuestionService.getAll();

        assertNotNull(result);
        assertEquals(testQuestions.size(), result.size());
        assertTrue(result.containsAll(testQuestions));
        verify(repository).getAll(QuestionType.MATH);
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion_WhenQuestionsExist() {
        List<Question> questionList = List.copyOf(testQuestions);
        when(repository.getAll(eq(QuestionType.MATH)))
                .thenReturn(questionList);

        Question result = mathQuestionService.getRandomQuestion();

        assertNotNull(result);
        assertTrue(questionList.contains(result));
        verify(repository).getAll(QuestionType.MATH);
    }

    @Test
    void allMethods_ShouldUseMathQuestionType() {
        when(repository.add(eq(QuestionType.MATH), anyString(), anyString()))
                .thenReturn(testQuestion);
        when(repository.remove(eq(QuestionType.MATH), any(Question.class)))
                .thenReturn(testQuestion);
        when(repository.getAll(eq(QuestionType.MATH)))
                .thenReturn(testQuestions);

        mathQuestionService.add("test", "answer");
        mathQuestionService.remove(testQuestion);
        mathQuestionService.getAll();

        verify(repository).add(eq(QuestionType.MATH), anyString(), anyString());
        verify(repository).remove(eq(QuestionType.MATH), any(Question.class));
        verify(repository).getAll(eq(QuestionType.MATH));
    }
}