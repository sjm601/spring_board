package com.ezen.springmvc.domain.board.service;

import java.util.List;

import com.ezen.springmvc.domain.board.dto.BoardDTO;

/**
 *게시판 관련 비즈니스 로직 처리 및 트랜잭션 관리
 * @author 박상훈
 *
 */
public interface BoardService {
	public void register(BoardDTO board);
	public List<BoardDTO> getBoardList();
	public BoardDTO findByBoardId(int boardId);
}
