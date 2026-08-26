package com.spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("student")
public class StudentDTO {
    private int student_id;
    @NotNull(message = "학번은 필수입니다.")
    @Min(1000)
    private Long student_no;
    @NotBlank(message = "학생 이름은 필수입니다.")
    private String student_name;
    private int dept_id;

    private String dept_name;

    private int grade;
    private String phone;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
