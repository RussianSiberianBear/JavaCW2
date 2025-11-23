package org.skypro.coursework2.exception;

public class QuestionsNumbersIsLargeException extends RuntimeException {

    public QuestionsNumbersIsLargeException(int size) {

        super("Количество вопросов превышает допустимое " + size);
    }

}
