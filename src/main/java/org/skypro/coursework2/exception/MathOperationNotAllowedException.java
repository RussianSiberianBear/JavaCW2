package org.skypro.coursework2.exception;

public class MathOperationNotAllowedException extends  RuntimeException {
    public MathOperationNotAllowedException() {
        super("Операция недопустима!");
    }
}
