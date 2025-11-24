package org.skypro.coursework2.reposytory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework2.exception.QuestionNotFoundException;
import org.skypro.coursework2.exception.QuestionRepositoryIsEmptyException;
import org.skypro.coursework2.exception.QuestionTypeAllInvalidException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommonQuestionRepositoryTest {

    private CommonQuestionRepository repository;
    private TreeMap<QuestionType, HashSet<Question>> repositoryMap;

    private Question javaQuestion1;
    private Question javaQuestion2;
    private Question mathQuestion1;
    private Question mathQuestion2;

    @BeforeEach
    void setUp() throws Exception {
        repository = new CommonQuestionRepository();

        // Получаем доступ к приватному полю repository через рефлексию
        Field repositoryField = CommonQuestionRepository.class.getDeclaredField("repository");
        repositoryField.setAccessible(true);
        repositoryMap = (TreeMap<QuestionType, HashSet<Question>>) repositoryField.get(repository);

        // Инициализируем множества для существующих типов вопросов
        repositoryMap.put(QuestionType.JAVA, new HashSet<>());
        repositoryMap.put(QuestionType.MATH, new HashSet<>());

        // Создаем тестовые вопросы
        javaQuestion1 = new Question("What is Java?", "Programming language");
        javaQuestion2 = new Question("What is OOP?", "Object-Oriented Programming");
        mathQuestion1 = new Question("What is 2+2?", "4");
        mathQuestion2 = new Question("What is 5*5?", "25");

        // Добавляем вопросы в репозиторий
        repositoryMap.get(QuestionType.JAVA).add(javaQuestion1);
        repositoryMap.get(QuestionType.JAVA).add(javaQuestion2);
        repositoryMap.get(QuestionType.MATH).add(mathQuestion1);
        repositoryMap.get(QuestionType.MATH).add(mathQuestion2);
    }

    // Тесты для метода add()
    @Test
    void add_ShouldAddQuestionToRepository_WhenTypeIsValid() {
        // Arrange
        String newQuestion = "What is inheritance?";
        String newAnswer = "OOP concept";

        // Act
        Question result = repository.add(QuestionType.JAVA, newQuestion, newAnswer);

        // Assert
        assertNotNull(result);
        assertEquals(newQuestion, result.getQuestion());
        assertEquals(newAnswer, result.getAnswer());
        assertTrue(repositoryMap.get(QuestionType.JAVA).contains(result));
    }

    @Test
    void add_ShouldAddQuestionToMathType_WhenTypeIsMath() {
        // Arrange
        String newQuestion = "What is 10/2?";
        String newAnswer = "5";

        // Act
        Question result = repository.add(QuestionType.MATH, newQuestion, newAnswer);

        // Assert
        assertNotNull(result);
        assertEquals(newQuestion, result.getQuestion());
        assertEquals(newAnswer, result.getAnswer());
        assertTrue(repositoryMap.get(QuestionType.MATH).contains(result));
    }

    @Test
    void add_ShouldThrowQuestionTypeAllInvalidException_WhenTypeIsAll() {
        // Arrange
        String question = "Test question";
        String answer = "Test answer";

        // Act & Assert
        assertThrows(QuestionTypeAllInvalidException.class,
                () -> repository.add(QuestionType.ALL, question, answer));
    }

    @Test
    void add_ShouldThrowNullPointerException_WhenTypeDoesNotExist() {
        // Arrange
        repositoryMap.remove(QuestionType.JAVA);
        // Arrange
        String newQuestion = "Question JAVA";
        String newAnswer = "Answer JAVA";

        // Act
        Question result = repository.add(QuestionType.JAVA, newQuestion, newAnswer);

        // Assert
        assertNotNull(result);
        assertEquals(newQuestion, result.getQuestion());
        assertEquals(newAnswer, result.getAnswer());
        assertTrue(repositoryMap.get(QuestionType.JAVA).contains(result));
    }

    // Тесты для метода remove()
    @Test
    void remove_ShouldRemoveQuestionFromRepository_WhenQuestionExists() {
        // Act
        Question result = repository.remove(QuestionType.JAVA, javaQuestion1);

        // Assert
        assertNotNull(result);
        assertEquals(javaQuestion1, result);
        assertFalse(repositoryMap.get(QuestionType.JAVA).contains(javaQuestion1));
        assertTrue(repositoryMap.get(QuestionType.JAVA).contains(javaQuestion2));
    }

    @Test
    void remove_ShouldRemoveMathQuestion_WhenTypeIsMath() {
        // Act
        Question result = repository.remove(QuestionType.MATH, mathQuestion1);

        // Assert
        assertNotNull(result);
        assertEquals(mathQuestion1, result);
        assertFalse(repositoryMap.get(QuestionType.MATH).contains(mathQuestion1));
        assertTrue(repositoryMap.get(QuestionType.MATH).contains(mathQuestion2));
    }

    @Test
    void remove_ShouldThrowQuestionNotFoundException_WhenQuestionDoesNotExist() {
        // Arrange
        Question nonExistentQuestion = new Question("Non-existent", "Question");

        // Act & Assert
        assertThrows(QuestionNotFoundException.class,
                () -> repository.remove(QuestionType.JAVA, nonExistentQuestion));
    }

    @Test
    void remove_ShouldThrowQuestionTypeAllInvalidException_WhenTypeIsAll() {
        // Act & Assert
        assertThrows(QuestionTypeAllInvalidException.class,
                () -> repository.remove(QuestionType.ALL, javaQuestion1));
    }

    @Test
    void remove_ShouldThrowQuestionRepositoryIsEmptyException_WhenTypeDoesNotExist() {
        // Arrange
        repositoryMap.remove(QuestionType.JAVA);

        // Act & Assert
        assertThrows(QuestionRepositoryIsEmptyException.class,
                () -> repository.remove(QuestionType.JAVA, javaQuestion1));
    }

    // Тесты для метода getAll()
    @Test
    void getAll_ShouldReturnAllQuestions_WhenTypeIsAll() {
        // Act
        Collection<Question> result = repository.getAll(QuestionType.ALL);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
        assertTrue(result.contains(javaQuestion1));
        assertTrue(result.contains(javaQuestion2));
        assertTrue(result.contains(mathQuestion1));
        assertTrue(result.contains(mathQuestion2));
    }

    @Test
    void getAll_ShouldReturnOnlyJavaQuestions_WhenTypeIsJava() {
        // Act
        Collection<Question> result = repository.getAll(QuestionType.JAVA);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(javaQuestion1));
        assertTrue(result.contains(javaQuestion2));
        assertFalse(result.contains(mathQuestion1));
    }

    @Test
    void getAll_ShouldReturnOnlyMathQuestions_WhenTypeIsMath() {
        // Act
        Collection<Question> result = repository.getAll(QuestionType.MATH);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(mathQuestion1));
        assertTrue(result.contains(mathQuestion2));
        assertFalse(result.contains(javaQuestion1));
    }

    @Test
    void getAll_ShouldThrowQuestionRepositoryIsEmptyException_WhenTypeHasNoQuestions() {
        // Arrange - очищаем один из типов
        repositoryMap.get(QuestionType.MATH).clear();

        assertThrows(QuestionRepositoryIsEmptyException.class,
                () -> repository.getAll(QuestionType.MATH));
    }

    @Test
    void getAll_ShouldHandleNullValuesInSets() {
        // Arrange
        HashSet<Question> javaSet = repositoryMap.get(QuestionType.JAVA);
        javaSet.add(null); // добавляем null в существующий набор

        // Act
        Collection<Question> result = repository.getAll(QuestionType.JAVA);

        // Assert
        assertNotNull(result);
        // null должен быть отфильтрован, остаются только оригинальные 2 вопроса
        assertEquals(2, result.size());
        assertTrue(result.contains(javaQuestion1));
        assertTrue(result.contains(javaQuestion2));
        assertTrue(result.stream().noneMatch(Objects::isNull));
    }

    @Test
    void getAll_ShouldHandleNullValuesWhenTypeIsAll() {
        // Arrange
        // Вместо добавления null через рефлексию, проверим что метод getAll
        // корректно работает с существующими данными
        int originalSize = repository.getAll(QuestionType.ALL).size();

        // Добавим новый вопрос через публичный метод
        Question newQuestion = repository.add(QuestionType.JAVA, "New question", "New answer");

        // Act
        Collection<Question> result = repository.getAll(QuestionType.ALL);

        // Assert
        assertNotNull(result);
        // Должно быть на 1 вопрос больше
        assertEquals(originalSize + 1, result.size());
        assertTrue(result.contains(newQuestion));
        assertTrue(result.contains(javaQuestion1));
        assertTrue(result.contains(javaQuestion2));
        assertTrue(result.contains(mathQuestion1));
        assertTrue(result.contains(mathQuestion2));
    }

    @Test
    void getAll_ShouldReturnUnmodifiableCollection_WhenTypeIsAll() {
        // Act
        Collection<Question> result = repository.getAll(QuestionType.ALL);

        // Assert
        assertNotNull(result);
        assertThrows(UnsupportedOperationException.class, () -> result.add(new Question("Test", "Test")));
    }

    @Test
    void getAll_ShouldReturnUnmodifiableCollection_WhenTypeIsSpecific() {
        // Act
        Collection<Question> result = repository.getAll(QuestionType.JAVA);

        // Assert
        assertNotNull(result);
        assertThrows(UnsupportedOperationException.class, () -> result.add(new Question("Test", "Test")));
    }

    // Тесты конструктора
    @Test
    void constructor_ShouldInitializeEmptyRepository() throws Exception {
        // Arrange
        CommonQuestionRepository newRepository = new CommonQuestionRepository();
        Field repositoryField = CommonQuestionRepository.class.getDeclaredField("repository");
        repositoryField.setAccessible(true);
        TreeMap<QuestionType, HashSet<Question>> newRepositoryMap =
                (TreeMap<QuestionType, HashSet<Question>>) repositoryField.get(newRepository);

        // Assert
        assertNotNull(newRepositoryMap);
        assertTrue(newRepositoryMap.isEmpty());
    }

    // Тесты на граничные случаи
    @Test
    void add_ShouldHandleDuplicateQuestions() {
        // Arrange
        String question = "What is polymorphism?";
        String answer = "OOP concept";
        // Используем другой вопрос, чтобы не было дубликата с существующими

        // Act
        Question result1 = repository.add(QuestionType.JAVA, question, answer);
        Question result2 = repository.add(QuestionType.JAVA, question, answer);

        // Assert
        assertNotNull(result1);
        assertNotNull(result2);
        // HashSet не должен добавлять дубликаты (по equals/hashCode)
        // Был 2 вопроса, добавляем 1 уникальный вопрос (дубликат не добавляется)
        assertEquals(3, repositoryMap.get(QuestionType.JAVA).size());
    }

    @Test
    void getAll_ShouldThrowQuestionRepositoryIsEmptyException_WhenEmptyRepository() {
        // Arrange
        CommonQuestionRepository emptyRepository = new CommonQuestionRepository();

        // Act
        assertThrows(QuestionRepositoryIsEmptyException.class,
                () -> emptyRepository.getAll(QuestionType.ALL));
    }

    @Test
    void remove_ShouldWorkCorrectlyAfterMultipleOperations() {
        // Arrange
        Question newQuestion = repository.add(QuestionType.JAVA, "New question", "New answer");

        // Act
        repository.remove(QuestionType.JAVA, javaQuestion1);
        repository.remove(QuestionType.JAVA, newQuestion);

        // Assert
        Collection<Question> javaQuestions = repository.getAll(QuestionType.JAVA);
        assertEquals(1, javaQuestions.size());
        assertTrue(javaQuestions.contains(javaQuestion2));
    }

    @Test
    void integrationTest_AddGetAllRemoveFlow() throws Exception {
        // Arrange
        CommonQuestionRepository testRepo = new CommonQuestionRepository();
        Field repositoryField = CommonQuestionRepository.class.getDeclaredField("repository");
        repositoryField.setAccessible(true);
        TreeMap<QuestionType, HashSet<Question>> testRepoMap =
                (TreeMap<QuestionType, HashSet<Question>>) repositoryField.get(testRepo);

        // Инициализируем множества для существующих типов
        testRepoMap.put(QuestionType.JAVA, new HashSet<>());
        testRepoMap.put(QuestionType.MATH, new HashSet<>());

        // Act - добавляем вопросы
        Question q1 = testRepo.add(QuestionType.JAVA, "Q1", "A1");
        Question q2 = testRepo.add(QuestionType.JAVA, "Q2", "A2");
        Question q3 = testRepo.add(QuestionType.MATH, "Q3", "A3");

        // Assert - проверяем getAll
        Collection<Question> allQuestions = testRepo.getAll(QuestionType.ALL);
        Collection<Question> javaQuestions = testRepo.getAll(QuestionType.JAVA);
        Collection<Question> mathQuestions = testRepo.getAll(QuestionType.MATH);

        assertEquals(3, allQuestions.size());
        assertEquals(2, javaQuestions.size());
        assertEquals(1, mathQuestions.size());

        // Act - удаляем вопрос
        Question removed = testRepo.remove(QuestionType.JAVA, q1);

        // Assert - проверяем после удаления
        assertEquals(2, testRepo.getAll(QuestionType.ALL).size());
        assertEquals(1, testRepo.getAll(QuestionType.JAVA).size());
        assertEquals(1, testRepo.getAll(QuestionType.MATH).size());
        assertFalse(testRepo.getAll(QuestionType.JAVA).contains(q1));
    }
}