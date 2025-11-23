package org.skypro.coursework2.exception;

public class ErrorRemoveOperationException extends RuntimeException {
    public ErrorRemoveOperationException() {
        super("Ошибка при выполнении операции удаления вопроса!");
    }
}
