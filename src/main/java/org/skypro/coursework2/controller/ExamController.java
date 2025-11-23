package org.skypro.coursework2.controller;

import org.skypro.coursework2.exception.QuestionCountValueMustGreaterZeroException;
import org.skypro.coursework2.service.ExaminerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    private final ExaminerServiceImpl examinerServiceImpl;

    public ExamController(ExaminerServiceImpl examinerServiceImpl) {
        this.examinerServiceImpl = examinerServiceImpl;
    }

    @GetMapping("/exam/java/get/{amount}")
    public ResponseEntity<?> getQuestions(@PathVariable String amount) {

        if ((amount.isBlank()) || !amount.matches("-?\\d+") || Integer.parseInt(amount) <= 0) {
            throw new QuestionCountValueMustGreaterZeroException();
        }
        return ResponseEntity.status(200).body(examinerServiceImpl.getQuestions(Integer.parseInt(amount)));
    }

}
