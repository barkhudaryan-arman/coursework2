package pro.sky.course2.course2.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.course2.dto.Question;

import java.util.*;

import static java.util.Collections.unmodifiableCollection;
@Service

public class JavaQuestionService implements QuestionService {
    private static final Random RANDOM = new Random();

    private final Set<Question> questionSet = new HashSet<>();


    @Override
    public Question add(Question question) {
        questionSet.add(question);
        return question;
    }

    @Override
    public String remove(String Question, String question) {
        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return unmodifiableCollection(questionSet);
    }

    @Override
    public Question getRandomQuestion() {
        if (questionSet.isEmpty()){
            throw new IllegalStateException("Хранилище с вопросами пустое");
        }
        int questionIndex = RANDOM.nextInt(questionSet.size());
        return new ArrayList<>(questionSet).get(questionIndex);
    }
}
