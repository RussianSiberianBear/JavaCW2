package org.skypro.coursework2.exception;

public class QuestionOrAnswerIsBlankException extends RuntimeException {

    public QuestionOrAnswerIsBlankException() {
        super(("Вопрос и ответ не должны быть пустой строкой!"));
    }

}
