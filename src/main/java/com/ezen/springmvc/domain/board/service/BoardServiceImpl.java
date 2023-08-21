package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper boardMapper;
	
	
	@Override
	@Transactional
	public void register(BoardDTO board) {
		boardMapper.create(board);
	}



	@Override
	public List<BoardDTO> getBoardList() {
		return boardMapper.findAll();
	}



	@Override
	public BoardDTO findByBoardId(int boardId) {
		return boardMapper.findByBoardId(boardId);
	}

	
}
