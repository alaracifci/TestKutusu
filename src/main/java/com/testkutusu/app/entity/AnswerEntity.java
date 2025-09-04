package com.testkutusu.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
