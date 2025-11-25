package org.skypro.coursework2.repository;

import org.skypro.coursework2.exception.QuestionNotFoundException;
import org.skypro.coursework2.exception.QuestionRepositoryIsEmptyException;
import org.skypro.coursework2.exception.QuestionTypeAllInvalidException;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CommonQuestionRepository implements QuestionRepository {

    private final TreeMap<QuestionType, HashSet<Question>> repository;

    public CommonQuestionRepository() {
        this.repository = new TreeMap<>();
    }

    @Override
    public Question add(QuestionType type, String question, String answer) {
        if (type == QuestionType.ALL) {
            throw new QuestionTypeAllInvalidException();
        }
        Question q = new Question(question, answer);
        if (repository.containsKey(type)) {
            repository.get(type).add(q);
        } else {
            HashSet<Question> set = new HashSet<>();
            set.add(q);
            repository.put(type, set);
        }
        return q;
    }

    @Override
    public Question remove(QuestionType type, Question question) {

        if (type == QuestionType.ALL) {
            throw new QuestionTypeAllInvalidException();
        }

        if (repository.isEmpty()) {
            throw new QuestionRepositoryIsEmptyException();
        }

        if (repository.containsKey(type)) {
            HashSet<Question> set = repository.get(type);
            if (set.isEmpty()) {
                throw new QuestionRepositoryIsEmptyException();
            }
            if (!set.contains(question)) {
                throw new QuestionNotFoundException();
            }
            set.remove(question);
            return question;
        } else {
            throw new QuestionRepositoryIsEmptyException();
        }
    }

    @Override
    public Collection<Question> getAll(QuestionType type) {

        if (type == QuestionType.ALL) {
            List<Question> allQuestions = repository.values().stream()
                    .filter(Objects::nonNull)
                    .flatMap(Set::stream)
                    .filter(Objects::nonNull)
                    .toList();

            if (allQuestions.isEmpty()) {
                throw new QuestionRepositoryIsEmptyException();
            }
            return allQuestions;
        } else {
            Set<Question> questions = repository.get(type);
            if (questions == null || questions.isEmpty()) {
                throw new QuestionRepositoryIsEmptyException();
            }
            return questions.stream()
                    .filter(Objects::nonNull)
                    .toList();
        }
    }

}
