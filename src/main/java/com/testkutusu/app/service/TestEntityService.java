package com.testkutusu.app.service;



import com.testkutusu.app.dto.StudentDto;
import com.testkutusu.app.dto.TestDto;
import com.testkutusu.app.entity.Student;
import com.testkutusu.app.entity.TestEntity;
import com.testkutusu.app.repository.TestEntityRepository;
import com.testkutusu.app.util.PasswordUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TestEntityService {
    private final TestEntityRepository testEntityRepository;

    public TestEntityService(TestEntityRepository testEntityRepository){
        this.testEntityRepository=testEntityRepository;
    }

    //bütün testeri getirme
    public List<TestEntity> getAllTestEntities(){
        return testEntityRepository.findAll();
    }

    //id'sine göre test getirme
    public TestEntity getTestEnityById(Long id){
        return testEntityRepository.findById(id).get();
    }

    //aktif olan testleri getirme
    public List<TestEntity> getAllActive(){
        return testEntityRepository.findByActiveTrue();
    }

    //test oluşturma
    public TestEntity saveTest(TestDto dto) {
        TestEntity test = TestEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .active(true)
                .createdAt(LocalDateTime.now()) // veya dto.getCreatedAt() kullanabilirsin
                .build();

        return testEntityRepository.save(test);
    }


    //testi güncelleme
    @Transactional
    public TestEntity updateTestEntity(Long testId, TestEntity testEntity){
        testEntity=testEntityRepository.findById(testId).get();
        return testEntityRepository.save(testEntity);
    }

    //testi silme
    @Transactional
    public void deleteTestEntityByID(Long id){
        testEntityRepository.deleteById(id);
    }

}
