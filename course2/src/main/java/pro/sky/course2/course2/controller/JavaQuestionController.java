package pro.sky.course2.course2.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.course2.course2.dto.Question;
import pro.sky.course2.course2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping("/add")
    public Question add(@RequestParam("QuestionText") String question,
                        @RequestParam("QuestionAnswer") String answer){
       return this.questionService.add(question,answer);
    }
    @GetMapping("/remove")
    public Question remove(@RequestParam("QuestionText") String question,
                        @RequestParam("QuestionAnswer") String answer){
        return this.questionService.remove(
                new Question(question, answer));
    }
    @GetMapping("/remove")
    public Collection<Question> getAll(){
        return this.questionService.getAll();
    }
}
