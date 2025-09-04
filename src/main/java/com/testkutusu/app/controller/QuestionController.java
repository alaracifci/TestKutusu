package com.testkutusu.app.controller;


import com.testkutusu.app.dto.QuestionDto;
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
    public QuestionDto addQuestionToTest(@PathVariable Long testId, @RequestBody QuestionDto questionDto){
        Question question=questionService.convertToEntity(testId,questionDto);
        return questionService.convertToDto(question);
    }

    //tüm soruları listele
    @GetMapping
    public List<QuestionDto> getAllQuestions(){
        return questionService.convertToDoList(questionService.getAllQuestions());
    }

    //belirli bir soru listele
    @GetMapping("/tests/{testId}/questions")
    public List<QuestionDto> getAllQuestionsByTest(@PathVariable Long testId){
        return questionService.convertToDoList(questionService.getQuestionByTest(testId));
    }

    //tek bir soruyu id ile getir
    @GetMapping("/questions/{id}")
    public QuestionDto getQuestionById(@PathVariable Long id){
        return questionService.convertToDto(questionService.getQuestionById(id));
    }

    //soru güncelle
    @PutMapping("/questions/{id}")
    public QuestionDto updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        Question question = questionService.convertToEntity(id,questionDto); // ← burası artık çalışacak
        Question updated = questionService.updateQuestion(id, question);
        return questionService.convertToDto(updated);
    }


    //soruyu sil
    @DeleteMapping("/questions/{id}")
    public void deleteQuestionById(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
    }
}
