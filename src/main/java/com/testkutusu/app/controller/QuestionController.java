package com.testkutusu.app.controller;


import com.testkutusu.app.entity.Question;
import com.testkutusu.app.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    //belirli bir teste soru ekle
    @PostMapping("/tests/{testId}/questions")
    public Question addQuestionToTest(@PathVariable Long testId, @RequestBody Question question){
        return questionService.addQuestionToTest(testId,question);
    }

    //tüm soruları listele
    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    //belirli bir soru listele
    @GetMapping("/tests/{testId}/questions")
    public List<Question> getAllQuestionsByTest(@PathVariable Long testId){
        return questionService.getQuestionByTest(testId);
    }

    //tek bir soruyu id ile getir
    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    //soru güncelle
    @PutMapping("/questions/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }
    //soruyu sil
    @DeleteMapping("/questions/{id}")
    public void deleteQuestionById(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
    }
}
