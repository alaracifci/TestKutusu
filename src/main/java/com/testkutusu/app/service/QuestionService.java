package com.testkutusu.app.service;


import com.testkutusu.app.entity.Question;
import com.testkutusu.app.entity.TestEntity;
import com.testkutusu.app.dto.QuestionDto;
import com.testkutusu.app.repository.QuestionRepository;
import com.testkutusu.app.repository.TestEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestEntityRepository testEntityRepository;

    public QuestionService(QuestionRepository questionRepository, TestEntityRepository testEntityRepository){
        this.questionRepository=questionRepository;
        this.testEntityRepository=testEntityRepository;
    }

    //teste soru ekleme
    public Question convertToEntity(Long testId, QuestionDto questionDto){
        TestEntity testEntity=testEntityRepository.findById(testId).orElseThrow(() -> new RuntimeException("test not found"));
        Question question=Question.builder()
                .text(questionDto.getText())
                .correctAnswer(questionDto.getCorrectAnswer())
                .optionA(questionDto.getOptionA())
                .optionB(questionDto.getOptionB())
                .optionC(questionDto.getOptionC())
                .optionD(questionDto.getOptionD())
                .optionE(questionDto.getOptionE())
                .testEntity(testEntity)
                .build();
        return questionRepository.save(question);
    }


    //entity DTO dönüşümü
    public QuestionDto convertToDto(Question question){
        return QuestionDto.builder()
                .id(question.getId())
                .text(question.getText())
                .correctAnswer(question.getCorrectAnswer())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .optionE(question.getOptionE())
                .testId(question.getTestEntity().getId())
                .build();
    }

    public List<QuestionDto> convertToDoList(List<Question>questions){
        return questions.stream().map(this::convertToDto).collect(Collectors.toList());
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
