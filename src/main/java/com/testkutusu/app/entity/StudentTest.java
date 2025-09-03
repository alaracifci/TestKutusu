package com.testkutusu.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id",nullable = false)//hangi öğrenci katıldı
    private Student student;

    @ManyToOne(optional = false)  //hangi teste katıldı
    @JoinColumn(name = "test_id",nullable = false)
    private TestEntity testEntity;

    private Double score; //öğrencinin testten aldığı puan
    private java.time.LocalDateTime participationDate;  //öğrencinin teste katılma tarihi

    //öğrencinin verdiği cevaplar
    @OneToMany(mappedBy = "studentTest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AnswerEntity> answerEntity;

}
