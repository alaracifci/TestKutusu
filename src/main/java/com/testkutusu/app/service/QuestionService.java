package com.testkutusu.app.service;


import com.testkutusu.app.entity.Question;
import com.testkutusu.app.entity.TestEntity;
import com.testkutusu.app.repository.QuestionRepository;
import com.testkutusu.app.repository.TestEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestEntityRepository testEntityRepository;

    public QuestionService(QuestionRepository questionRepository, TestEntityRepository testEntityRepository){
        this.questionRepository=questionRepository;
        this.testEntityRepository=testEntityRepository;
    }

    //teste soru ekleme
    public Question addQuestionToTest(Long testId, Question question){
        TestEntity testEntity=testEntityRepository.findById(testId).orElseThrow(() -> new RuntimeException("test not found"));
        question.setTestEntity(testEntity);
        return questionRepository.save(question);

    }

    //tüm soruları listele
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    //test id'sine göre soru listeleme
    public List<Question> getQuestionByTest(Long testId){
        return questionRepository.findByTestEntity_Id(testId);
    }

    //id'sine göre soru getirme
    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(()-> new RuntimeException("question not found"));
    }

    //soru güncelleme
    public Question updateQuestion(Long questionId, Question question){
        Question existingQuestion=questionRepository.findById(questionId).orElseThrow(()-> new RuntimeException("Question not found"));

        existingQuestion.setText(question.getText());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
        existingQuestion.setOptionA(question.getOptionA());
        existingQuestion.setOptionB(question.getOptionB());
        existingQuestion.setOptionC(question.getOptionC());
        existingQuestion.setOptionD(question.getOptionD());
        existingQuestion.setOptionE(question.getOptionE());

        if (question.getCorrectAnswer()!=null){
            existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
        }
        return questionRepository.save(existingQuestion);
    }

    //soru silme
    public void deleteQuestionById(Long id){
        if(questionRepository.existsById(id)){
            questionRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("question not found with id "+id);
        }
    }

}
