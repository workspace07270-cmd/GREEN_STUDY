package com.spring.service;

import com.spring.dto.DepartmentDTO;
import com.spring.dto.StudentDTO;
import com.spring.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentMapper studentmapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentmapper = studentMapper;
    }

    public List<StudentDTO> findAll() {
        return studentmapper.findAll();
    }

    public List<StudentDTO> search(String keyword, String category) {
        return studentmapper.search(keyword, category);
    }

    public List<DepartmentDTO> findAllDepartments() {
        return studentmapper.findAllDepartments();
    }

    public void insert(StudentDTO student) {
        studentmapper.insert(student);
    }

    public StudentDTO findById(int studentId) {
        return studentmapper.findById(studentId);
    }

    public void update(StudentDTO student) {
        studentmapper.update(student);
    }

    public void delete(int studentId) {
        studentmapper.delete(studentId);
    }
}
