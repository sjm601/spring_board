package com.ezen.springmvc;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.board.dto.BoardDTO;	
import com.ezen.springmvc.domain.board.mapper.BoardMapper;
import com.ezen.springmvc.domain.member.dto.Member;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardMapperTest {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	@DisplayName("전체 게시판 조회")
	//@Disabled
	public void findAllTest(){
		List<BoardDTO> list = boardMapper.findAll();
		list.forEach(board->{
			log.info("검색된 전체 게시판 : {}",board);
		});
		//assertThat(list).isNotNull().size();
	}

	

	@Test
	@DisplayName("게시판 등록")
	@Disabled
	public void CreateTest(){
		BoardDTO createBoard = BoardDTO.builder()
											.boardId(0)
											.category(1)
											.title("아스날 우승 기원 게시판")
											.description("아스날의 23-24 시즌 우승을 바라는 게시판입니다..")
											.build();
		boardMapper.create(createBoard);
		log.debug("게시판 등록 완료 : {}",createBoard);
	
	}
	
	@Test
		public void findByIdTest() {
			// given
			int boardId = 10;
			// when
			BoardDTO boardDTO = boardMapper.findByBoardId(boardId);
			// then
			assertThat(boardDTO)
				.isNotNull();
			log.info("게시판정보 : {}", boardDTO.toString());
	}
		
}







