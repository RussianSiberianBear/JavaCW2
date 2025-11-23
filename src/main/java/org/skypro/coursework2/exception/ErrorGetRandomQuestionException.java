package org.skypro.coursework2.exception;

public class ErrorGetRandomQuestionException extends RuntimeException {
    public ErrorGetRandomQuestionException() {
        super("Ошибка при получении случайного вопроса!");
    }
}
