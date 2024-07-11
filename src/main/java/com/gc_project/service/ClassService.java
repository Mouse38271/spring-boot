package com.gc_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gc_project.model.ClassModel;
import com.gc_project.repository.ClassRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public ClassModel createClass(ClassModel newClass) {
        return classRepository.save(newClass);
    }
}
