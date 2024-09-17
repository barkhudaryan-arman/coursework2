package pro.sky.course2.course2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2.course2.dto.Question;
import pro.sky.course2.course2.exceptions.IncorrectQuestionAmountException;

import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {
    private static final Random RANDOM = new Random();
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @ParameterizedTest
    @ValueSource(ints = {5,100})
    void getQuestionTest(int amount) {
        //given
        Collection<Question> questionCollection = IntStream.generate(RANDOM::nextInt)
                .limit(amount - 1)
                .boxed()
                .map(Object::toString)
                .map(s -> new Question(s, s))
                .toList();
        Mockito.when(questionService.getRandomQuestion()).thenAnswer(invocationOnMock -> new Question(
                RANDOM.nextLong() + "", RANDOM.nextLong() + ""));
        //when
        Collection<Question> actualQuestions = examinerService.getQuestions(amount);
        //then
        Assertions.assertEquals(amount, actualQuestions.size());
        verify(questionService, atLeast(amount)).getRandomQuestion();
        int actualQuestionCount = questionService.getAll().size();
        int expectedQuestionCount = questionCount + 3;
    }
    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void getQuestionNegativeTest(int amount) {
        Assertions.assertThrows(IncorrectQuestionAmountException.class, () ->
                examinerService.getQuestions(amount));
    }
    @ParameterizedTest
    @ValueSource(ints = {5,10})
    void getQuestionNegative1Test(int amount) {
        //given
        Collection<Question> questionCollection = IntStream.generate(RANDOM::nextInt)
                .limit(amount - 1)
                        .boxed()
                                .map(Object::toString)
                                        .map(s -> new Question(s, s))
                                                .toList();
        Mockito.when(questionService.getAll()).thenReturn(questionCollection);
        //when
        Collection<Question> actualQuestions = examinerService.getQuestions(amount);
        //then
        Assertions.assertThrows(IncorrectQuestionAmountException.class, () ->
                examinerService.getQuestions(amount));
    }
}
