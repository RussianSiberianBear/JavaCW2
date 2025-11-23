package org.skypro.coursework2.service;

import org.skypro.coursework2.exception.*;
import org.skypro.coursework2.interfaces.QuestionService;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CommonQuestionService implements QuestionService {
    protected CommonQuestionRepository repository;
    protected final QuestionType type;

    public CommonQuestionService(QuestionType type, CommonQuestionRepository repository) {
        this.repository = repository;
        this.type = type;
    }

    @Override
    public Question add(String question, String answer) {
        try {
            return repository.add(this.type, question, answer);
        } catch (Exception e) {
            throw new ErrorAddOperationException();
        }
    }

    @Override
    public Question remove(Question question) {
        try {
            return repository.remove(this.type, question);
        } catch (Exception e) {
            throw new ErrorRemoveOperationException();
        }
    }

    @Override
    public Collection<Question> getAll() {
        try {
            return repository.getAll(this.type);
        } catch (QuestionNotFoundException e) {
            // Пробрасываем QuestionNotFoundException дальше
            throw e;
        } catch (Exception e) {
            throw new ErrorGetAllQuestionException();
        }
    }

    @Override
    public Question getRandomQuestion() {
        try {
            Collection<Question> questions = this.getAll();
            List<Question> questionList = questions instanceof List ?
                    (List<Question>) questions : new ArrayList<>(questions);
            return questionList.get(new Random().nextInt(questionList.size()));
        } catch (Exception e) {
            throw new ErrorGetRandomQuestionException();
        }
    }
}
