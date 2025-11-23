package org.skypro.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionOrAnswerIsBlankException.class)
    public ResponseEntity<?> handleQuestionAndAnswerException(QuestionOrAnswerIsBlankException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(MethodNotAllowedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(QuestionTypeAllInvalidException.class)
    public ResponseEntity<?> handleQuestionTypeAllInvalidException(QuestionTypeAllInvalidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionsNumbersIsLargeException.class)
    public ResponseEntity<?> handleQuestionsNumbersIsLargeException(QuestionsNumbersIsLargeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionCountValueMustGreaterZeroException.class)
    public ResponseEntity<?> handleQuestionsNumbersIsLargeException(QuestionCountValueMustGreaterZeroException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<?> handleQuestionNotFoundException(QuestionNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorAddOperationException.class)
    public ResponseEntity<?> handleErrorAddOperationException(ErrorAddOperationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorRemoveOperationException.class)
    public ResponseEntity<?> handleErrorRemoveOperationException(ErrorRemoveOperationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorGetAllQuestionException.class)
    public ResponseEntity<?> handleErrorGetAllQuestionException(ErrorGetAllQuestionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MathOperationNotAllowedException.class)
    public ResponseEntity<?> handleMathOperationNotAllowedException(MathOperationNotAllowedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(QuestionRepositoryIsEmptyException.class)
    public ResponseEntity<?> handleQuestionRepositoryIsBlankException(QuestionRepositoryIsEmptyException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
