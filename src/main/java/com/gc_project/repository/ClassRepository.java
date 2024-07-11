package com.gc_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gc_project.model.ClassModel;

@Repository
public interface ClassRepository extends JpaRepository<ClassModel, Long> {
    ClassModel findByClassCode(String classCode);
}
