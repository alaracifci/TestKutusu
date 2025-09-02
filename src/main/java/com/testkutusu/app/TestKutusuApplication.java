package com.testkutusu.app;


import com.testkutusu.app.entity.*;
import com.testkutusu.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class TestKutusuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestKutusuApplication.class, args);
    }

    @Bean
    CommandLineRunner test(StudentRepository studentRepository,
                           TestEntityRepository testEntityRepository,
                           QuestionRepository questionRepository,
                           AnswerRepository answerRepository,
                           StudentTestRepository studentTestRepository) {
        return args -> {
            // Student oluştur ve kaydet
            Student student = new Student();
            student.setFirstName("Alara");  // Düzeltildi: "FirsName" değil "FirstName"
            student.setLastName("Çifçi");
            student.setStudentNumber("123");
            student.setEmail("cifcialara@gmail.com");
            student.setPassword("nak89");
            student.setActive(true);
            Student savedStudent = studentRepository.save(student);

            // TestEntity oluştur ve kaydet
            TestEntity testEntity = new TestEntity();
            testEntity.setTitle("deneme testi");
            TestEntity savedTest = testEntityRepository.save(testEntity);

            // Question oluştur, testEntity'yi set et ve kaydet
            Question question = new Question();
            question.setText("Deneme 1");
            question.setCorrectAnswer("B");  // Zorunlu alan
            question.setOptionA("Seçenek A");
            question.setOptionB("Seçenek B");
            question.setOptionC("Seçenek C");
            question.setOptionD("Seçenek D");
            question.setOptionE("Seçenek E");
            question.setTestEntity(savedTest);
            questionRepository.save(question);

            // AnswerEntity oluştur ve kaydet
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.setText("programlama");
            answerEntity.setQuestion(question);
            answerRepository.save(answerEntity);

            // StudentTest oluştur, student ve testEntity'yi set et ve kaydet
            StudentTest studentTest = new StudentTest();
            studentTest.setStudent(savedStudent);  // BURASI ÖNEMLİ: student set edilmeli
            studentTest.setTestEntity(savedTest);
            studentTest.setParticipationDate(LocalDateTime.now());
            studentTestRepository.save(studentTest);

            // Veri kontrol çıktılarını yazdır
            System.out.println("Öğrenciler:");
            studentRepository.findAll().forEach(System.out::println);

            System.out.println("Testler:");
            testEntityRepository.findAll().forEach(System.out::println);

            System.out.println("Sorular:");
            questionRepository.findAll().forEach(System.out::println);

            System.out.println("Cevaplar:");
            answerRepository.findAll().forEach(System.out::println);

            System.out.println("Student Testler:");
            studentTestRepository.findAll().forEach(System.out::println);
        };
    }
}

