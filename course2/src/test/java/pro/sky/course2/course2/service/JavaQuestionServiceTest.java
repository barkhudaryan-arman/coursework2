package pro.sky.course2.course2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.course2.course2.dto.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @Test
    void addTest() {
        //given
        int questionCount = questionService.getAll().size();
        //when
        questionService.add(new Question(null, null));
        questionService.add(new Question(null, ""));
        questionService.add(new Question("", ""));
        //then
        int actualQuestionCount = questionService.getAll().size();
        int expectedQuestionCount = questionCount + 3;
        Assertions.assertEquals(expectedQuestionCount, actualQuestionCount);
    }

    @Test
    void removeTest() {
        //given
        questionService.add(new Question(null, null));
        questionService.add(new Question(null, ""));
        questionService.add(new Question("", ""));
        int questionCount = questionService.getAll().size();
        Set<Question> questionToRemove = questionService.getAll()
                .stream()
                .limit(questionCount - 1)
                .collect(Collectors.toSet());
        //when
        questionToRemove.forEach(questionService::remove);
        //then
        Collection<Question> actualQuections = questionService.getAll();
        Assertions.assertEquals(1, actualQuections.size());

        boolean isRemovingCorrect = actualQuections.stream()
                .noneMatch(questionToRemove::contains);
        assertTrue(isRemovingCorrect);
    }
    @Test
    void getRandomQuestionTest() {
        //given
        Set<Question> questionToRemove = new HashSet<>(questionService.getAll());
        //when
        questionToRemove.forEach(questionService::remove);
        //then
        assertThrows(IllegalAccessException.class,questionService::getRandomQuestion);
    }
}
