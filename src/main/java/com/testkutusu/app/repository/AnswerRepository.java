package com.testkutusu.app.repository;


import com.testkutusu.app.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
    List<AnswerEntity> findByQuestionId(Long questionId);



}
