package org.skypro.coursework2.model;

import java.util.Objects;

public class Question {

    private final String question;
    private final String answer;
    private final int hash;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
        int result = Objects.hash(question, answer);
        this.hash = (result == 0 ? 1 : result);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Question other)
                && Objects.equals(question, other.question)
                && Objects.equals(answer, other.answer);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return "Question{question=\"" + question + ", answer=\"" + answer + "\"}";
    }

}
