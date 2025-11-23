package org.skypro.coursework2.interfaces;

import org.skypro.coursework2.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Service
public interface ExaminerService {

    Collection<Question> getQuestions(@PathVariable Integer amount);

}
