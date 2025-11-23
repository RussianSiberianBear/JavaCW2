package org.skypro.coursework2.exception;

public class ErrorGetAllQuestionException extends RuntimeException {
    public ErrorGetAllQuestionException() {
        super("Ошибка при получении списка вопросов!");
    }
}
