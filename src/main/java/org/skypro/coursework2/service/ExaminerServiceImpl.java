package org.skypro.coursework2.service;

import org.skypro.coursework2.exception.QuestionsNumbersIsLargeException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.skypro.coursework2.repository.CommonQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final CommonQuestionRepository repository;

    public ExaminerServiceImpl(CommonQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Question> getQuestions(@PathVariable Integer amount) {

        ArrayList<Question> questions = new ArrayList<>(repository.getAll(QuestionType.ALL));
        ArrayList<Question> result = new ArrayList<>();

        int size = questions.size();
        if (amount > size) {
            throw new QuestionsNumbersIsLargeException(size);
        }
        Collections.shuffle(questions); // перемешали элементы для надёжности случайного выбора
        Random random = new Random();
        while (result.size() < amount) {
            result.add(questions.get(random.nextInt(questions.size())));
        }
        return result;
    }

}
