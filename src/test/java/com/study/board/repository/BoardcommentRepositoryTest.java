package com.study.board.repository;

import com.study.board.entity.Boardcomment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class BoardcommentRepositoryTest {

    @Autowired
    BoardcommentRepository boardcommentRepository;
    @Test
    void findByBoardId() {
        List<Boardcomment> page = boardcommentRepository.findByid(2, Sort.by(Sort.Direction.DESC, "id"));

        for(int i=0; i < page.size() ; i++){
            System.out.println(
                    page.get(i)
            );
        }

    }
}