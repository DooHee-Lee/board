package com.study.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardcommentPK implements Serializable {

    @Column(name = "commentid")
    private Integer commentid; //코멘트ID

    @Column(name = "id")
    private Integer id; //게시글ID

}
