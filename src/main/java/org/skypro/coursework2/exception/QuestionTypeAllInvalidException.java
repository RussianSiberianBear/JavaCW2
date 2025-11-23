package org.skypro.coursework2.exception;

public class QuestionTypeAllInvalidException  extends RuntimeException{

    public QuestionTypeAllInvalidException() {
            super("При данной операции тип вопроса ALL недопустим!");
        }
    }
