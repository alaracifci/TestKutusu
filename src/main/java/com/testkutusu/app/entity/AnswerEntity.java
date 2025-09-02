package com.testkutusu.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne  //hangi soru için cevap verildi
    private Question question;
    private String studentAnswer; //öğrencinin verdiği cevap
    private boolean isCorrect; //doğru mu yanlış mı

    @ManyToOne  //cevabın hangi test katılımına ait olduğu
    private StudentTest studentTest;

}
