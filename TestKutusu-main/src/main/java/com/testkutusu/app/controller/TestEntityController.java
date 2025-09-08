package com.testkutusu.app.controller;


import com.testkutusu.app.entity.TestEntity;
import com.testkutusu.app.service.TestEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestEntityController {

    private final TestEntityService testEntityService;

    public TestEntityController(TestEntityService testEntityService){
        this.testEntityService=testEntityService;
    }

    //yeni bir test ekleme
    @PostMapping("/create")
    public TestEntity createTestEntity(@RequestBody TestEntity testEntity){
        return testEntityService.createTestEntity(testEntity);
    }

    //bütün testleri listeleme
    @GetMapping
    public ResponseEntity<List<TestEntity>> getAllTestEntity(){
        return ResponseEntity.ok(testEntityService.getAllActive());
    }

    //id'sine göre test getirme
    @GetMapping("/{id}")
    public ResponseEntity<TestEntity> getTestEntityById(@PathVariable Long id){
        return ResponseEntity.ok(testEntityService.getTestEnityById(id));
    }

    //testleri güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<TestEntity> updatTestEntityById(@PathVariable Long id, @RequestBody TestEntity test){
        return ResponseEntity.ok(testEntityService.updateTestEntity(id,test));
    }

    //id'sine göre test silme
    @DeleteMapping("/{id}")
    public void deleteTestEntityById(@PathVariable Long id){
        testEntityService.deleteTestEntityByID(id);
    }
}
