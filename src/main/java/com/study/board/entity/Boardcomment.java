package com.study.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@IdClass(BoardcommentPK.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boardcomment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentid; //코멘트ID

    @Id
    private Integer id; //게시글ID

    private String commentname; //코멘트작성자
    private String commentcontent; //코멘트내용
    private Date registdate; //등록일자

}
