package pro.sky.course2.course2.service;

import pro.sky.course2.course2.dto.Question;

import java.util.Collection;

public interface QuestionService {
    default Question add(String question, String answer) {
        return this.add(new Question(question, answer));
    }

    Question add(Question question);

    String remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandomQuestion();

    Question remove(Question question);
}

