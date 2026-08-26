package com.example.studentapi.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ApiResponseTest {

    @Test
    void successResponseHasUnifiedShape() {
        ApiResponse<String> response = ApiResponse.success("학생 데이터");

        assertThat(response.success()).isTrue();
        assertThat(response.code()).isEqualTo("SUCCESS");
        assertThat(response.message()).isEqualTo("요청에 성공했습니다.");
        assertThat(response.data()).isEqualTo("학생 데이터");
    }

    @Test
    void errorResponseHasUnifiedShape() {
        ApiResponse<Void> response = ApiResponse.error(ErrorCode.STUDENT_NOT_FOUND);

        assertThat(response.success()).isFalse();
        assertThat(response.code()).isEqualTo("STUDENT_NOT_FOUND");
        assertThat(response.data()).isNull();
    }
}
