package com.testkutusu.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //her sorunun kendi id'si olacak
    private String text;

    @Column(nullable = false)
    private String correctAnswer; //doğru cevap

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;

    //bir soru sadece bir teste bağlıdır
    //bir testin içinde birçok soru olabiir
    @ManyToOne
    @JoinColumn(name="test_id",nullable = false)  //question tablosuna test_id diye bir kolon eklenir.bu kolon TestEntity ile ilişkiyi sağlar
    private TestEntity testEntity;


}
