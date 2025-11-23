package org.skypro.coursework2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework2.exception.QuestionsNumbersIsLargeException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private CommonQuestionRepository repository;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_ShouldReturnExactAmountOfQuestions_WhenAmountIsValid() {
        // Arrange
        int amount = 3;
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.size());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldReturnCorrectAmount_WhenAmountEqualsTotalSize() {
        // Arrange
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );
        int amount = allQuestions.size();

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.size());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldReturnSingleQuestion_WhenAmountIsOne() {
        // Arrange
        int amount = 1;
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountIsGreaterThanTotalQuestions() {
        // Arrange
        int amount = 5;
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act & Assert
        assertThrows(
                QuestionsNumbersIsLargeException.class,
                () -> examinerService.getQuestions(amount)
        );

        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldReturnEmptyCollection_WhenAmountIsZeroAndNoQuestionsAvailable() {
        // Arrange
        int amount = 0;
        List<Question> allQuestions = List.of();

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountIsGreaterThanZeroAndNoQuestionsAvailable() {
        // Arrange
        int amount = 1;
        List<Question> allQuestions = List.of();

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act & Assert
        assertThrows(
                QuestionsNumbersIsLargeException.class,
                () -> examinerService.getQuestions(amount)
        );

        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldReturnEmptyCollection_WhenAmountIsZeroAndQuestionsExist() {
        // Arrange
        int amount = 0;
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldReturnRandomQuestions_WhenCalledMultipleTimes() {
        // Arrange
        int amount = 2;
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result1 = examinerService.getQuestions(amount);
        Collection<Question> result2 = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(amount, result1.size());
        assertEquals(amount, result2.size());
        verify(repository, times(2)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldThrowException_WhenRequestedAmountExceedsAvailableQuestions() {
        // Arrange
        int amount = 3;
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act & Assert
        assertThrows(
                QuestionsNumbersIsLargeException.class,
                () -> examinerService.getQuestions(amount)
        );

        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void getQuestions_ShouldWorkWithSetCollection() {
        // Arrange
        int amount = 2;
        Set<Question> allQuestions = Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.size());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }

    @Test
    void constructor_ShouldInitializeRepository() {
        // Act & Assert
        assertNotNull(examinerService);
    }

    @Test
    void getQuestions_ShouldHandleEdgeCase_WhenAmountIsLargeButValid() {
        // Arrange
        List<Question> allQuestions = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5")
        );
        int amount = allQuestions.size() - 1;

        when(repository.getAll(QuestionType.ALL)).thenReturn(allQuestions);

        // Act
        Collection<Question> result = examinerService.getQuestions(amount);

        // Assert
        assertNotNull(result);
        assertEquals(amount, result.size());
        verify(repository, times(1)).getAll(QuestionType.ALL);
    }
}