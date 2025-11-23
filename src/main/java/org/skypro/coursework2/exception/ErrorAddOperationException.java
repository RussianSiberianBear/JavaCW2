package org.skypro.coursework2.exception;

public class ErrorAddOperationException extends RuntimeException{
    public ErrorAddOperationException(){
        super("Ошибка при операции добавления вопроса!");
    }
}
