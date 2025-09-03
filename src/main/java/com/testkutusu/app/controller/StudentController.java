package com.testkutusu.app.controller;

import com.testkutusu.app.entity.Student;
import com.testkutusu.app.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    // yeni öğrenci ekleme - kullanıcı tarafından
    @PostMapping("/register")
    public Student register(@Valid @RequestParam String firstName, String lastName, String studentNumber, String email, String password){
        return studentService.register(firstName, lastName, studentNumber, email, password);
    }
    //Login
    @PostMapping("/login")
    public Student login(@RequestParam @NotBlank(message = "Öğrenci numarası boş bırakılamaz") String studentNumber, @RequestParam @NotBlank(message = "Şifre boş bırakılamaz") String password){
        return studentService.login(studentNumber, password);
    }

    /// CRUD işlemleri

    //öğrenci oluşturma - admin
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
