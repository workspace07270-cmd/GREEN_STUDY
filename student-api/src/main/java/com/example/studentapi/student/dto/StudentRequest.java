package com.example.studentapi.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotBlank(message = "학과 번호는 필수입니다.")
        @Pattern(regexp = "^[A-Za-z0-9]{3}$", message = "학과 번호는 영문 또는 숫자 3자리여야 합니다.")
        String majorNo,

        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 10, message = "이름은 10자 이하여야 합니다.")
        String name,

        @Pattern(regexp = "^01[016789][0-9]{7,8}$", message = "전화번호는 하이픈 없이 10~11자리여야 합니다.")
        String phone
) {
}
