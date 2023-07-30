package com.study.board.repository;

import com.study.board.entity.Boardcomment;
import com.study.board.entity.BoardcommentPK;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardcommentRepository extends JpaRepository<Boardcomment, BoardcommentPK> {
    List<Boardcomment> findByid(Integer id, Sort by);
}
