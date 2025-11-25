package org.skypro.coursework2.exception;

public class QuestionRepositoryIsEmptyException extends RuntimeException {

    public QuestionRepositoryIsEmptyException() {
        super("Ошибка! Репозиторий вопросов и ответов пуст!");
    }
}
