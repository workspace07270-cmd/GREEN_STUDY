package com.spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("board")
@Getter
@Setter
public class BoardDTO {
    private int bno;

    @NotBlank(message = "제목을 반드시 입력하셔야합니다.")
    private String title;

    @NotBlank(message = "내용을 반드시 입력하셔야합니다.")
    private String content;

    private String write_date;
    private String write_update_date;
    private int bcount;
    private int blike;
    private int bhate;
    private Long mid;
    private String nickname;

}

