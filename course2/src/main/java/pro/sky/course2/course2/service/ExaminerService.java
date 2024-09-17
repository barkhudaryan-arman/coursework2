package pro.sky.course2.course2.service;

import pro.sky.course2.course2.dto.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question>getQuestions(int amount);
}
