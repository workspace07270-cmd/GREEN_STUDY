package com.example.studentapi.student.dto;

public record StudentResponse(
        String no,
        String majorNo,
        String majorName,
        String name,
        String phone
) {
}
