package com.testkutusu.app.repository;


import com.testkutusu.app.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
    AnswerEntity findByQuestionId(Long questionId);

}
