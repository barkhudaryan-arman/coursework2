package pro.sky.course2.course2.service;

import org.springframework.stereotype.Service;
import pro.sky.course2.course2.dto.Question;
import pro.sky.course2.course2.exceptions.IncorrectQuestionAmountException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 1 || amount > questionService.getAll().size()){
            throw new IncorrectQuestionAmountException();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return null;
    }
}
