package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.entity.Boardcomment;
import com.study.board.service.BoardService;
import com.study.board.service.BoardcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardcommentService boardcommentService;

    @GetMapping("/board/write") //글쓰기 페이지로 이동
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writedo") //글쓰기페이지에서 데이터 받아서 Insert 처리
    public String boardWritePro(Board board){

        System.out.println("제목 : " + board.getTitle());
        System.out.println("내용 : "+ board.getContent());

        boardService.boardWrite(board);

        return "redirect:/board/list";
    }

    @PostMapping("/board/registcomment/{id}") //댓들등록
    public String boardcommentWrite(Boardcomment boardcomment, @PathVariable("id") Integer id){

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        boardcomment.setRegistdate(currentTimestamp);

        System.out.println("내용 : "+ boardcomment.getCommentid());
        System.out.println("내용 : "+ boardcomment.getId());
        System.out.println("내용 : "+ boardcomment.getCommentname());
        System.out.println("내용 : "+ boardcomment.getCommentcontent());
        System.out.println("내용 : "+ boardcomment.getRegistdate());

        boardcommentService.boardcommentWrite(boardcomment);

        return "redirect:/board/view/{id}";
    }



    @GetMapping("/board/list") //리스트페이지
    public String boardList(Model model, @PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> list = boardService.boardList(pageable);

        //현재 패이지 기준으로 앞 4페이지 , 뒤 5페이지
        int curPage = list.getPageable().getPageNumber() + 1;
        int staPage = Math.max(curPage - 4 , 1);
        int finPage = Math.min(curPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("curPage", curPage);
        model.addAttribute("staPage", staPage);
        model.addAttribute("finPage", finPage);

        return "boardlist";
    }

    @GetMapping("/board/view/{id}") //상세보기 페이지
    public String boardView(Model model, @PathVariable("id") Integer id){

        List<Boardcomment> list = boardcommentService.commentList(id);

        model.addAttribute("board", boardService.boardView(id));
        model.addAttribute("boardcommentlist", list);

        return "boardview";
    }

    @GetMapping("/board/delete") //삭제처리 후 list 페이지로 이동
    public String boardDelete(Integer id){

        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}") //수정페이지로 이동
    public String boardModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}") //수정페이지에서 데이터 받아서 UPDATE
    public String boardUpdate(@PathVariable("id") Integer id, Board board){
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.boardWrite(boardTemp);

        return "redirect:/board/list";
    }
}
