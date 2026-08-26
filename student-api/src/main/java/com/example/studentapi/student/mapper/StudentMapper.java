package com.example.studentapi.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.studentapi.student.dto.StudentRequest;
import com.example.studentapi.student.dto.StudentResponse;

public interface StudentMapper {
    List<StudentResponse> findAll();

    StudentResponse findByNo(String no);

    boolean existsByNo(String no);

    boolean existsMajorByNo(String majorNo);

    int insert(@Param("no") String no, @Param("request") StudentRequest request);

    int update(@Param("no") String no, @Param("request") StudentRequest request);

    int deleteByNo(String no);
}
