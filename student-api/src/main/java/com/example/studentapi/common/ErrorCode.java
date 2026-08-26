package com.example.studentapi.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
    MALFORMED_JSON(HttpStatus.BAD_REQUEST, "요청 JSON 형식이 올바르지 않습니다."),
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "학생을 찾을 수 없습니다."),
    MAJOR_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 학과입니다."),
    STUDENT_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 등록된 학번입니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "데이터 처리 중 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }
}
