package com.spring.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("boardCommentReq")
@Getter
@Setter
public class BoardCommentReactionReq {
    private int id;
    private int cno;
    private String type;
    private Long mid;

}