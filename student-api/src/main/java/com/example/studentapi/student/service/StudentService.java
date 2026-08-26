package com.example.studentapi.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentapi.common.BusinessException;
import com.example.studentapi.common.ErrorCode;
import com.example.studentapi.student.dto.StudentRequest;
import com.example.studentapi.student.dto.StudentResponse;
import com.example.studentapi.student.mapper.StudentMapper;

@Service
@Transactional(readOnly = true)
public class StudentService {
    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public List<StudentResponse> getStudents() {
        return studentMapper.findAll();
    }

    public StudentResponse getStudent(String no) {
        StudentResponse student = studentMapper.findByNo(no);
        if (student == null) {
            throw new BusinessException(ErrorCode.STUDENT_NOT_FOUND);
        }
        return student;
    }

    @Transactional
    public StudentResponse createStudent(String no, StudentRequest request) {
        if (studentMapper.existsByNo(no)) {
            throw new BusinessException(ErrorCode.STUDENT_ALREADY_EXISTS);
        }
        validateMajor(request.majorNo());
        studentMapper.insert(no, request);
        return getStudent(no);
    }

    @Transactional
    public StudentResponse updateStudent(String no, StudentRequest request) {
        ensureStudentExists(no);
        validateMajor(request.majorNo());
        studentMapper.update(no, request);
        return getStudent(no);
    }

    @Transactional
    public void deleteStudent(String no) {
        ensureStudentExists(no);
        studentMapper.deleteByNo(no);
    }

    private void ensureStudentExists(String no) {
        if (!studentMapper.existsByNo(no)) {
            throw new BusinessException(ErrorCode.STUDENT_NOT_FOUND);
        }
    }

    private void validateMajor(String majorNo) {
        if (!studentMapper.existsMajorByNo(majorNo)) {
            throw new BusinessException(ErrorCode.MAJOR_NOT_FOUND);
        }
    }
}
