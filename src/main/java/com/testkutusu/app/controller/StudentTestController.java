package com.testkutusu.app.controller;


import com.testkutusu.app.entity.StudentTest;
import com.testkutusu.app.service.StudentTestService;
import com.testkutusu.app.dto.StudentTestDto;
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
    @PostMapping
    public StudentTestDto addStudentTest(@RequestBody StudentTestDto dto){
        StudentTest studentTest = studentTestService.assignStudentToTest(
                dto.getStudentId(),
                dto.getTestId(),
                dto.getScore()   // JSON'dan gelen score
        );
        return studentTestService.convertToDto(studentTest);
    }

    //belirli bir öğrencini çözdüğü testlerin listesini getirir
    @GetMapping("/student/{studentId}")
    public List<StudentTestDto> getStudentTestsByStudentId(@PathVariable Long studentId){
        List<StudentTest> tests=studentTestService.getStudentTestsByStudentId(studentId);
        return studentTestService.convertToDoList(tests);
    }

    //belirli bir testin tüm öğrenci kayıtlarını getirir
    @GetMapping("/test/{testId}")
    public List<StudentTestDto> getStudentTestsByTestId(@PathVariable Long testId){
        List<StudentTest> tests=studentTestService.getStudentTestsByTestId(testId);
        return studentTestService.convertToDoList(tests);
    }

    //skor güncelleme
    @PutMapping("/student/{studentId}/test/{testId}/score")
    public StudentTestDto updateScore(@PathVariable Long studentId,
                                      @PathVariable Long testId,
                                      @RequestBody StudentTestDto dto){
        StudentTest studentTest = studentTestService.updateScore(
                studentId,
                testId,
                dto.getScore()   // JSON'dan gelen yeni score
        );
        return studentTestService.convertToDto(studentTest);
    }

    //kayıt silme
    @DeleteMapping("/student/{studentId}/test/{testId}")
    public void deleteStudentTest(@PathVariable Long studentId, @PathVariable Long testId) {
        studentTestService.deleteStudentTestById(studentId, testId);
    }

}
