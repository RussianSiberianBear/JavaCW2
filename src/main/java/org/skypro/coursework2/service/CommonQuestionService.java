package org.skypro.coursework2.service;

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
        return repository.add(this.type, question, answer);
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(this.type, question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll(this.type);
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = this.getAll();
        List<Question> questionList = questions instanceof List ?
                (List<Question>) questions : new ArrayList<>(questions);
        return questionList.get(new Random().nextInt(questionList.size()));
    }
}
