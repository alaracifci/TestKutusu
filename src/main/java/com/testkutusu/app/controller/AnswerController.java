package com.testkutusu.app.controller;


import com.testkutusu.app.entity.AnswerEntity;
import com.testkutusu.app.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService){
        this.answerService=answerService;
    }

    //yeni bir cevap ekleme
    @PostMapping("/new/{questionId}")
    public AnswerEntity createAnswer(@PathVariable Long questionId, @RequestBody AnswerEntity answer){
        return answerService.addAnswerToQuestion(questionId,answer);
    }

    //tüm cevapları listeleme
    @GetMapping
    public List<AnswerEntity> getAllAnswers(){
        return answerService.getAllAnswers();
    }

    //bir sorunun cevabını almak
    @GetMapping("/answers/question/{questionId}")
    public List<AnswerEntity> getAnswerByQuestionId(@PathVariable Long questionId){
        return answerService.getAnswerByQuestionId(questionId);
    }


    //cevabı güncellemek
    @PutMapping("/answers/{id}")
    public AnswerEntity updateAnswer(@PathVariable Long id, @RequestBody AnswerEntity answer){
        return answerService.updateAnswerToQuestion(id,answer);
    }

    //cevabı silmek
    @DeleteMapping("/answers/{id}")
    public void deleteAnswer(@PathVariable Long id){
        answerService.deleteAnswerById(id);
    }

}
