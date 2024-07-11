package com.gc_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gc_project.model.ClassModel;
import com.gc_project.service.ClassService;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/create")
    public ClassModel createClass(@RequestBody ClassModel newClass) {
        return classService.createClass(newClass);
    }
}
