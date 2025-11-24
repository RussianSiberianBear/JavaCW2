package org.skypro.coursework2.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
        super("Вопрос не найден!");
    }
}
