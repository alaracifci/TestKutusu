package com.testkutusu.app.controller;

import com.testkutusu.app.entity.Student;
import com.testkutusu.app.service.StudentService;
import org.springframework.web.bind.annotation.*;
import com.testkutusu.app.dto.StudentDto;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public  StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    //Register
    // yeni öğrenci ekleme
    @PostMapping("/register")
    public Student register(@RequestParam String firstName, String lastName, String studentNumber, String email, String password){
        return studentService.register(firstName, lastName, studentNumber, email, password);
    }
    //Login
    @PostMapping("/login")
    public Student login(@RequestParam String studentNumber, @RequestParam String password){
        return studentService.login(studentNumber, password);
    }

    /// CRUD işlemleri

    //öğrenci oluşturma
    @PostMapping("/create")
    public Student createStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    //tüm öğrencileri listeleme
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    //id'ye göre öğrenci bul
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    //öğrenci güncelleme
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        return studentService.updateStudent(studentId,student);
    }

    //öğrenci silme
    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}
