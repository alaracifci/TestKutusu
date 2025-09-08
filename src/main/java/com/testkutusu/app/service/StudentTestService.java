package com.testkutusu.app.service;


import com.testkutusu.app.dto.StudentTestDto;
import com.testkutusu.app.entity.Student;
import com.testkutusu.app.entity.StudentTest;
import com.testkutusu.app.entity.TestEntity;
import com.testkutusu.app.repository.StudentRepository;
import com.testkutusu.app.repository.StudentTestRepository;
import com.testkutusu.app.repository.TestEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;
    private final StudentRepository studentRepository;
    private final TestEntityRepository testEntityRepository;

    public StudentTestService(StudentTestRepository studentTestRepository, StudentRepository studentRepository, TestEntityRepository testEntityRepository){
        this.studentTestRepository=studentTestRepository;
        this.studentRepository=studentRepository;
        this.testEntityRepository=testEntityRepository;
    }

    //öğrenciyi teste kaydet
    public StudentTest assignStudentToTest(Long studentId, Long testId){
        Student student=studentRepository.findById(studentId).orElseThrow(()-> new RuntimeException("student not found"));
        TestEntity testEntity=testEntityRepository.findById(testId).orElseThrow(()-> new RuntimeException("test not found"));

        StudentTest studentTest=new StudentTest();
        studentTest.setStudent(student);
        studentTest.setTestEntity(testEntity);
        studentTest.setScore(0.0);

        return studentTestRepository.save(studentTest);
    }

    //belirli bir öğrencinin çözdüğü testler
    public List<StudentTest> getStudentTestsByStudentId(Long studentId){
        return studentTestRepository.findByStudent_Id(studentId);
    }

    //belirli bir testi çözen öğrenciler
    public List<StudentTest> getStudentTestsByTestId(Long testId){
        return studentTestRepository.findByTestEntity_Id(testId);
    }

    //skor güncelleme
    public StudentTest updateScore(Long studentId, Long testId, Double newScore){
        StudentTest studentTest=studentTestRepository.findByStudent_IdAndTestEntity_Id(studentId,testId).orElseThrow(()-> new RuntimeException("studenttest not found"));
        studentTest.setScore(newScore);
        return studentTestRepository.save(studentTest);

    }

    //kayıt silme
    public void deleteStudentTestById(Long studentId, Long testId) {
        studentTestRepository.deleteByStudent_IdAndTestEntity_Id(studentId, testId);
    }


    //entity-Dto dönüşümü
    public StudentTestDto convertToDto(StudentTest studentTest){
        List<Long> answerIds= null;
        if (studentTest.getAnswerEntity() != null){
            answerIds=studentTest.getAnswerEntity().stream().map(answer -> answer.getId()).collect(Collectors.toList());
        }
        return StudentTestDto.builder()
                .id(studentTest.getId())
                .studentId(studentTest.getStudent().getId())
                .testId(studentTest.getTestEntity().getId())
                .score(studentTest.getScore())
                .participationDate(studentTest.getParticipationDate())
                .answerIds(answerIds)
                .build();
    }

    //Listeyi DTO listesine çevirme
    public List<StudentTestDto> convertToDoList(List<StudentTest> studentTest){
        return studentTest.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
