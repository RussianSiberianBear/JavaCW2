package org.skypro.coursework2.interfaces;

import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.model.QuestionType;

import java.util.Collection;

public interface QuestionRepository {

    Question add(QuestionType type, String question, String answer);
    Question remove(QuestionType type, Question question);
    Collection<Question> getAll(QuestionType type);

}
