package com.testkutusu.app.repository;


import com.testkutusu.app.entity.StudentTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTestRepository extends JpaRepository<StudentTest,Long> {

    List<StudentTest> findByStudent_Id(Long studentId);
    List<StudentTest> findByTestEntity_Id(Long testId);
    Optional<StudentTest> findByStudent_IdAndTestEntity_Id(Long studentId, Long testId);
    void deleteByStudent_IdAndTestEntity_Id(Long studentId, Long testId);



}
