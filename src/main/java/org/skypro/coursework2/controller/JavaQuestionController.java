package org.skypro.coursework2.controller;

import org.skypro.coursework2.exception.QuestionOrAnswerIsBlankException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.service.JavaQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService service;

    public JavaQuestionController(JavaQuestionService service) {

        this.service = service;
    }

    @GetMapping("/add?")
    public ResponseEntity<?> addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {

        if ((question.isBlank()) || answer.isBlank()) {
            throw new QuestionOrAnswerIsBlankException();
        }
        return ResponseEntity.status(200).body(service.add(question, answer));
    }

    @GetMapping("/remove?")
    public ResponseEntity<?> removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {

        if ((question.isBlank()) || answer.isBlank()) {
            throw new QuestionOrAnswerIsBlankException();
        }
        return ResponseEntity.status(200).body(service.remove(new Question(question, answer)));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findQuestion() {

        return ResponseEntity.status(200).body(service.getAll());
    }

}
