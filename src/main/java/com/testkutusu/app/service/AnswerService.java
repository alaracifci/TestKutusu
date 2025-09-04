package com.testkutusu.app.service;


import com.testkutusu.app.dto.AnswerDto;
import com.testkutusu.app.entity.AnswerEntity;
import com.testkutusu.app.entity.Question;
import com.testkutusu.app.repository.AnswerRepository;
import com.testkutusu.app.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository){
        this.answerRepository=answerRepository;
        this.questionRepository=questionRepository;
    }



    //DTO -> entity
    public AnswerEntity addAnswer(AnswerDto dto) {
        Long questionId = dto.getQuestionId();

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        AnswerEntity answer = AnswerEntity.builder()
                .text(dto.getText())
                .isCorrect(dto.getIsCorrect())
                .question(question)
                .build();

        return answerRepository.save(answer);
    }

    //Entity -> DTO
    public AnswerDto convertToDTo(AnswerEntity answer){
        return AnswerDto.builder()
                .id(answer.getId())
                .text(answer.getText())
                .studentAnswer(answer.getStudentAnswer())
                .isCorrect(answer.isCorrect())
                .questionId(answer.getQuestion().getId())
                .build();
    }

    //Listeleme
    public List<AnswerDto> convertToDoList(List<AnswerEntity> answers){
        return answers.stream().map(this::convertToDTo).collect(Collectors.toList());
    }

    //cevapları listeleme
    public List<AnswerEntity> getAllAnswers(){
        return answerRepository.findAll();
    }

    //tek cevap almak
    public List<AnswerEntity> getAnswerByQuestionId(Long questionId){
        return answerRepository.findByQuestionId(questionId);
    }

    //cevabı güncelleme
    public AnswerEntity updateAnswerToQuestion(Long AnswerId, AnswerDto dto){
        AnswerEntity existingAnswer=answerRepository.findById(AnswerId).orElseThrow(()-> new RuntimeException("Answer not found"));
        existingAnswer.setStudentAnswer(dto.getStudentAnswer());
        existingAnswer.setCorrect(dto.getIsCorrect());
        return answerRepository.save(existingAnswer);
    }

    //cevap sil
    public void deleteAnswerById(Long id){
        answerRepository.deleteById(id);
    }
}
