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
class JavaQuestionServiceTest {

    @Mock
    private CommonQuestionRepository repository;

    private JavaQuestionService javaQuestionService;

    private Question testQuestion;
    private Collection<Question> testQuestions;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService(repository);
        testQuestion = new Question("What is Java?", "Programming language");
        testQuestions = Set.of(
                testQuestion,
                new Question("What is OOP?", "Object-Oriented Programming"),
                new Question("What is JVM?", "Java Virtual Machine")
        );
    }

    @Test
    void constructor_ShouldInitializeWithJavaType() {
        Question result = new Question("test", "answer");
        when(repository.add(eq(QuestionType.JAVA), eq("test"), eq("answer")))
                .thenReturn(result);

        javaQuestionService.add("test", "answer");

        verify(repository).add(QuestionType.JAVA, "test", "answer");
    }

    @Test
    void add_ShouldReturnQuestion_WhenRepositorySucceeds() {
        String question = "What is polymorphism?";
        String answer = "OOP concept";
        Question expectedQuestion = new Question(question, answer);

        when(repository.add(eq(QuestionType.JAVA), eq(question), eq(answer)))
                .thenReturn(expectedQuestion);

        Question result = javaQuestionService.add(question, answer);

        assertEquals(expectedQuestion, result);
        verify(repository).add(QuestionType.JAVA, question, answer);
    }

    @Test
    void add_ShouldThrowErrorAddOperationException_WhenRepositoryThrowsException() {
        String question = "What is inheritance?";
        String answer = "OOP concept";

        when(repository.add(eq(QuestionType.JAVA), eq(question), eq(answer)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(ErrorAddOperationException.class,
                () -> javaQuestionService.add(question, answer));

        verify(repository).add(QuestionType.JAVA, question, answer);
    }

    @Test
    void remove_ShouldReturnRemovedQuestion_WhenRepositorySucceeds() {
        when(repository.remove(eq(QuestionType.JAVA), eq(testQuestion)))
                .thenReturn(testQuestion);

        Question result = javaQuestionService.remove(testQuestion);

        assertEquals(testQuestion, result);
        verify(repository).remove(QuestionType.JAVA, testQuestion);
    }

    @Test
    void remove_ShouldThrowErrorRemoveOperationException_WhenRepositoryThrowsException() {
        when(repository.remove(eq(QuestionType.JAVA), eq(testQuestion)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(ErrorRemoveOperationException.class,
                () -> javaQuestionService.remove(testQuestion));

        verify(repository).remove(QuestionType.JAVA, testQuestion);
    }

    @Test
    void getAll_ShouldReturnQuestionCollection_WhenRepositorySucceeds() {
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenReturn(testQuestions);

        Collection<Question> result = javaQuestionService.getAll();

        assertNotNull(result);
        assertEquals(testQuestions.size(), result.size());
        assertTrue(result.containsAll(testQuestions));
        verify(repository).getAll(QuestionType.JAVA);
    }

    @Test
    void getAll_ShouldThrowErrorGetAllQuestionException_WhenRepositoryThrowsException() {
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(ErrorGetAllQuestionException.class,
                () -> javaQuestionService.getAll());

        verify(repository).getAll(QuestionType.JAVA);
    }

    @Test
    void getAll_ShouldThrowErrorGetAllQuestionException_WhenRepositoryIsEmpty() {
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenThrow(new QuestionRepositoryIsEmptyException());

        assertThrows(ErrorGetAllQuestionException.class,
                () -> javaQuestionService.getAll());

        verify(repository).getAll(QuestionType.JAVA);
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion_WhenQuestionsExist() {
        List<Question> questionList = List.copyOf(testQuestions);
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenReturn(questionList);

        Question result = javaQuestionService.getRandomQuestion();

        assertNotNull(result);
        assertTrue(questionList.contains(result));
        verify(repository).getAll(QuestionType.JAVA);
    }

    @Test
    void getRandomQuestion_ShouldThrowErrorGetRandomQuestionException_WhenGetAllThrowsException() {
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(ErrorGetRandomQuestionException.class,
                () -> javaQuestionService.getRandomQuestion());

        verify(repository).getAll(QuestionType.JAVA);
    }

    @Test
    void getRandomQuestion_ShouldThrowErrorGetRandomQuestionException_WhenRepositoryIsEmpty() {
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenThrow(new QuestionRepositoryIsEmptyException());

        assertThrows(ErrorGetRandomQuestionException.class,
                () -> javaQuestionService.getRandomQuestion());

        verify(repository).getAll(QuestionType.JAVA);
    }

    @Test
    void allMethods_ShouldUseJavaQuestionType() {
        when(repository.add(eq(QuestionType.JAVA), anyString(), anyString()))
                .thenReturn(testQuestion);
        when(repository.remove(eq(QuestionType.JAVA), any(Question.class)))
                .thenReturn(testQuestion);
        when(repository.getAll(eq(QuestionType.JAVA)))
                .thenReturn(testQuestions);

        javaQuestionService.add("test", "answer");
        javaQuestionService.remove(testQuestion);
        javaQuestionService.getAll();

        verify(repository).add(eq(QuestionType.JAVA), anyString(), anyString());
        verify(repository).remove(eq(QuestionType.JAVA), any(Question.class));
        verify(repository).getAll(eq(QuestionType.JAVA));
    }
}