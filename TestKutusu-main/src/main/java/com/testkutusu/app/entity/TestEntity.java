package com.testkutusu.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  //test adı zorunlu
    private String title;

    @Column(length = 1000) //isteğe bağlı açıklama
    private String description;

    @Column(nullable = false)  //aktiflik
    private boolean active=true;

    @Column(nullable = false,updatable = false)  //sadece ilk oluşturulduğunda atanacak(oluşturulma tarihi)
    private LocalDateTime createdAt;

    @PrePersist  //bu method entity veritabanına kaydedilmeden hemen önce çalışır
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
    }
}
