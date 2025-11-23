package org.skypro.coursework2.service;

import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class JavaQuestionService extends CommonQuestionService {

    public JavaQuestionService(CommonQuestionRepository repository) {
        super(QuestionType.JAVA, repository);
    }

}
