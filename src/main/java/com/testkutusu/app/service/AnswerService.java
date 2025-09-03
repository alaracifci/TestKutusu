package com.testkutusu.app.service;


import com.testkutusu.app.entity.AnswerEntity;
import com.testkutusu.app.entity.Question;
import com.testkutusu.app.repository.AnswerRepository;
import com.testkutusu.app.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository){
        this.answerRepository=answerRepository;
        this.questionRepository=questionRepository;
    }

    //cevapları listeleme
    public List<AnswerEntity> getAllAnswers(){
        return answerRepository.findAll();
    }

    //tek cevap almak
    public List<AnswerEntity> getAnswerByQuestionId(Long questionId){
        return answerRepository.findByQuestionId(questionId);
    }

    //yeni bir cevap ekleme
    public AnswerEntity addAnswerToQuestion(Long questionId, AnswerEntity answerEntity){
        Question question=questionRepository.findById(questionId).orElseThrow(()-> new RuntimeException("Question not found"));
        answerEntity.setQuestion(question);
        return answerRepository.save(answerEntity);
    }

    //cevabı güncelleme
    public AnswerEntity updateAnswerToQuestion(Long AnswerId, AnswerEntity answerEntity){
        AnswerEntity existingAnswer=answerRepository.findById(AnswerId).orElseThrow(()-> new RuntimeException("Answer not found"));
        existingAnswer.setStudentAnswer(answerEntity.getStudentAnswer());
        existingAnswer.setCorrect(answerEntity.isCorrect());
        return answerRepository.save(existingAnswer);
    }

    //cevap sil
    public void deleteAnswerById(Long id){
        answerRepository.deleteById(id);
    }
}
