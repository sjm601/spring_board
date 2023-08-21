package com.ezen.springmvc;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void getBoardListTest() {
		List<BoardDTO> list =boardService.getBoardList();
		log.info("전체 게시판 정보: {}",list);
	}
}
