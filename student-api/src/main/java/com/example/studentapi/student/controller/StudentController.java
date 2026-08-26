package com.example.studentapi.student.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentapi.common.ApiResponse;
import com.example.studentapi.student.dto.StudentRequest;
import com.example.studentapi.student.dto.StudentResponse;
import com.example.studentapi.student.service.StudentService;

@Validated
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ApiResponse<List<StudentResponse>> getStudents() {
        return ApiResponse.success(studentService.getStudents());
    }

    @GetMapping("/{no}")
    public ApiResponse<StudentResponse> getStudent(@PathVariable("no") @Pattern(regexp = "^[0-9]{8}$") String no) {
        return ApiResponse.success(studentService.getStudent(no));
    }

    @PostMapping("/{no}")
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @PathVariable("no") @Pattern(regexp = "^[0-9]{8}$") String no,
            @Valid @RequestBody StudentRequest request) {
        StudentResponse created = studentService.createStudent(no, request);
        return ResponseEntity.created(URI.create("/api/students/" + no))
                .body(ApiResponse.success("학생이 등록되었습니다.", created));
    }

    @PutMapping("/{no}")
    public ApiResponse<StudentResponse> updateStudent(
            @PathVariable("no") @Pattern(regexp = "^[0-9]{8}$") String no,
            @Valid @RequestBody StudentRequest request) {
        return ApiResponse.success("학생 정보가 수정되었습니다.", studentService.updateStudent(no, request));
    }

    @DeleteMapping("/{no}")
    public ApiResponse<Void> deleteStudent(@PathVariable("no") @Pattern(regexp = "^[0-9]{8}$") String no) {
        studentService.deleteStudent(no);
        return ApiResponse.success("학생이 삭제되었습니다.", null);
    }
}
