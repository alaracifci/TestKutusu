package com.testkutusu.app.controller;


import com.testkutusu.app.dto.AnswerDto;
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
    public AnswerDto createAnswer( @RequestBody AnswerDto dto){
        AnswerEntity answer=answerService.addAnswer(dto);
        return answerService.convertToDTo(answer);
    }

    //tüm cevapları listeleme
    @GetMapping
    public List<AnswerDto> getAllAnswers(){
        return answerService.convertToDoList(answerService.getAllAnswers());
    }

    //bir sorunun cevabını almak
    @GetMapping("/answers/question/{questionId}")
    public List<AnswerDto> getAnswerByQuestionId(@PathVariable Long questionId){
        return answerService.convertToDoList(answerService.getAnswerByQuestionId(questionId));
    }


    //cevabı güncellemek
    @PutMapping("/answers/{id}")
    public AnswerDto updateAnswer(@PathVariable Long id, @RequestBody AnswerDto dto){
        AnswerEntity update=answerService.updateAnswerToQuestion(id,dto);
        return answerService.convertToDTo(update);
    }

    //cevabı silmek
    @DeleteMapping("/answers/{id}")
    public void deleteAnswer(@PathVariable Long id){
        answerService.deleteAnswerById(id);
    }

}
