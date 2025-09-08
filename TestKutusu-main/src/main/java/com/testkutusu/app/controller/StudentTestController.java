package com.testkutusu.app.controller;


import com.testkutusu.app.entity.StudentTest;
import com.testkutusu.app.service.StudentTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentTests")
public class StudentTestController {

    private final StudentTestService studentTestService;

    public StudentTestController(StudentTestService studentTestService){
        this.studentTestService=studentTestService;
    }

    //yeni kayıt(öğrenci-test eşleşmesi)
    @PostMapping("/student/{studentId}/test/{testId}")
    public StudentTest addStudentTest(@PathVariable Long studentId, @PathVariable Long testId){
        return studentTestService.assignStudentToTest(studentId,testId);
    }

    //belirli bir öğrencini çözdüğü testlerin listesini getirir
    @GetMapping("/student/{studentId}")
    public List<StudentTest> getStudentTestsByStudentId(@PathVariable Long studentId){
        return studentTestService.getStudentTestsByStudentId(studentId);
    }

    //belirli bir testin tüm öğrenci kayıtlarını getirir
    @GetMapping("/test/{testId}")
    public List<StudentTest> getStudentTestsByTestId(@PathVariable Long testId){
        return studentTestService.getStudentTestsByTestId(testId);
    }

    //skor güncelleme
    @PutMapping("/student/{studentId}/test/{testId}/score/{newScore}")
    public StudentTest updateScore(@PathVariable Long studentId, @PathVariable Long testId, @PathVariable Double newScore){
        return studentTestService.updateScore(studentId,testId,newScore);
    }

    //kayıt silme
    @DeleteMapping("/student/{studentId}/test/{testId}")
    public void deleteStudentTest(@PathVariable Long studentId, @PathVariable Long testId) {
        studentTestService.deleteStudentTestById(studentId, testId);
    }

}
