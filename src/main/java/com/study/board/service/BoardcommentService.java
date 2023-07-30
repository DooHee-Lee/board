package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.Boardcomment;
import com.study.board.repository.BoardcommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardcommentService {

    @Autowired
    private BoardcommentRepository boardcommentRepository;

    public List<Boardcomment> commentList(Integer id){
        return boardcommentRepository.findByid( id , Sort.by(Sort.Direction.DESC, "commentid"));
    }

    //글 작성 처리
    public void boardcommentWrite(Boardcomment boardcomment){

        boardcommentRepository.save(boardcomment);
    }

}
