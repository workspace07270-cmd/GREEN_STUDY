package com.spring.mapper;

import com.spring.dto.DepartmentDTO;
import com.spring.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<StudentDTO> findAll();

    List<StudentDTO> search(@Param("keyword") String keyword,
                            @Param("category") String category);

    List<DepartmentDTO> findAllDepartments();

    int insert(StudentDTO student);

    StudentDTO findById(@Param("studentId") int studentId);

    int update(StudentDTO student);

    int delete(@Param("studentId") int studentId);
}
