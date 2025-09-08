package com.testkutusu.app.repository;


import com.testkutusu.app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByTestEntity_Id(Long testId);


}
