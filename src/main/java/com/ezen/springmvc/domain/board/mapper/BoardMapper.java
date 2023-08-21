package com.ezen.springmvc.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.member.dto.Member;

/**
 * board 테이블 관련 명세
 * @author 박상훈
 */
@Mapper
public interface BoardMapper {
	
	/** 신규 게시판 생성 */
	public void create(BoardDTO boardDTO);
	
	/** 전체 게시판 목록 반환 */
	public List<BoardDTO> findAll();
	
	/** 게시판 조회 */
	public BoardDTO findByBoardId(int boardId);
	

}