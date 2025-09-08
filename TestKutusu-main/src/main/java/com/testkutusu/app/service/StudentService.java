package com.testkutusu.app.service;

import com.testkutusu.app.entity.Student;
import com.testkutusu.app.repository.StudentRepository;
import com.testkutusu.app.util.PasswordUtil;
import com.testkutusu.app.dto.StudentDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    /// Register & Login ///
    // register
    public Student register(String firstName, String lastName, String studentNumber, String email, String rawPassword){
        if(firstName.isEmpty() || lastName.isEmpty() || studentNumber.isEmpty() || email.isEmpty() || rawPassword.isEmpty()){
            throw new RuntimeException("Boşalan bırakılamaz!");
        }
        if (studentRepository.findByStudentNumber(studentNumber).isPresent()){
            throw new RuntimeException("Öğrenci zaten kayıtlı!");
        }
        String hashedPassword= PasswordUtil.hashPassword(rawPassword);
        Student student=new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setStudentNumber(studentNumber);
        student.setEmail(email);
        student.setPassword(hashedPassword);
        return studentRepository.save(student);
    }
    // login
    public Student login(String studentNumber, String rawPassword){
        Student student=studentRepository.findByStudentNumber(studentNumber).orElseThrow(()-> new RuntimeException("Öğrenci bulunamadı!"));
        String hashedInput=PasswordUtil.hashPassword(rawPassword);
        if (!hashedInput.equals(student.getPassword())){
            throw new RuntimeException("Şifre yanlış!");
        }
        return student;
    }

    /////// CRUD işlemleri ///////
    //öğrenci oluşturma
    public Student saveStudent(StudentDto dto) {
        if (studentRepository.existsByStudentNumber(dto.studentNumber())) {
            throw new RuntimeException("Öğrenci numarası zaten kayıtlı!");
        }
        String hashedPassword = dto.password() != null ? PasswordUtil.hashPassword(dto.password()) : null;

        Student student = Student.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(hashedPassword)
                .studentNumber(dto.studentNumber())  // Burada mutlaka set et
                .active(true)
                .build();

        return studentRepository.save(student);
    }


    //öğrencileri listeleme
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //id'sine göre öğrenci getirme
    public Student getStudentById(Long id){
        Optional<Student> optionalStudent=studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else {
            throw new RuntimeException("Öğrenci bulunamadı!");
        }
    }

    //öğrenci kaydetme
    public Student saveStudent(Student student){
        if (student.getPassword() !=null){
            student.setPassword(PasswordUtil.hashPassword(student.getPassword()));
        }
        return studentRepository.save(student);
    }

    //öğrenci bilgileri güncelleme
    public Student updateStudent(Long studentId, Student student){
        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
        return studentRepository.save(student);
    }
        else {
        throw new RuntimeException("Öğrenci bulunamadı!");
    }
}

    //öğrenci silme
    public void deleteStudentById(Long id){
        if (!studentRepository.existsById(id)){
            throw new RuntimeException("Öğrenci bulunamadı!");
        }
        studentRepository.deleteById(id);
    }


}
