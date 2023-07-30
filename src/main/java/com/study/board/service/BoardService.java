package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성 처리
    public void boardWrite(Board board){

        boardRepository.save(board);
    }

    //게시글 리스트 조회
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    // 특정 게시글 상세조회
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    // 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}
