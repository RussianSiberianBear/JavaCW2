package org.skypro.coursework2.service;

import org.skypro.coursework2.exception.MathOperationNotAllowedException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MathQuestionService extends CommonQuestionService {

    public MathQuestionService(CommonQuestionRepository repository) {
        super(QuestionType.MATH, repository);
    }

    // Для проверки выброса исключений надо раскомментировать и проверять в MathQuestionServiceTest2
/*
    @Override
    public Question add(String question, String answer) {
        throw new MathOperationNotAllowedException();
    }

    @Override
    public Question remove(Question question) {
        throw new MathOperationNotAllowedException();
    }

    @Override
    public Collection<Question> getAll() {
        throw new MathOperationNotAllowedException();
    }

    @Override
    public Question getRandomQuestion() {
        throw new MathOperationNotAllowedException();
    }

*/
}
