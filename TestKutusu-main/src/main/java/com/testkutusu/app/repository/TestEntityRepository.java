package com.testkutusu.app.repository;

import com.testkutusu.app.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity,Long> {
    List<TestEntity> findByActiveTrue();

}
