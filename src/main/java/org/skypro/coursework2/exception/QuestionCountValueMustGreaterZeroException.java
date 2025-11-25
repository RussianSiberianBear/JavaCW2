package org.skypro.coursework2.exception;

public class QuestionCountValueMustGreaterZeroException extends RuntimeException {

    public QuestionCountValueMustGreaterZeroException() {
        super("Количество запрашиваемых вопросов должно быть целым положительным значением!");
    }
}
