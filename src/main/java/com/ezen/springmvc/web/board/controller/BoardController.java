package com.ezen.springmvc.web.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.service.ArticleService;
import com.ezen.springmvc.domain.board.service.BoardService;
import com.ezen.springmvc.domain.common.web.PageParams;
import com.ezen.springmvc.domain.common.web.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	private final ArticleService articleService;
	
	
	//게시판 화면
	@GetMapping("/{id}")
	public String readBoard(@PathVariable("id") int id,@RequestParam(name = "page", defaultValue = "1") int page,Model model) {
		BoardDTO board =boardService.findByBoardId(id);
		model.addAttribute("board", board);
		List<BoardDTO> list= boardService.getBoardList();
		model.addAttribute("list",list);
		int count = articleService.getCountAll(id, null);
		PageParams pageParams = PageParams.builder().elementSize(5).pageSize(5).requestPage(page).rowCount(count).boardId(id).keyword(null).build();
		List<ArticleDTO> articleList=articleService.findByAll(pageParams);
		model.addAttribute("articleList", articleList);
		Pagination pagination = new Pagination(pageParams);
		model.addAttribute("pagination", pagination);
		return "board/list";  
	}
	
	//게시판 등록 화면
	@GetMapping("/{id}/register")
	public String register (@PathVariable("id") int id, Model model) {
		BoardDTO board =boardService.findByBoardId(id);
		model.addAttribute("board", board);
		List<BoardDTO> list = boardService.getBoardList();
		model.addAttribute("list",list);
		return "board/register";
	}
	
	//게시글 상세 화면
	@GetMapping("/{id}/read/{articleId}")
	public String read(@PathVariable int id, @PathVariable int articleId, Model model) {
		BoardDTO board = boardService.findByBoardId(id);
		model.addAttribute("board",board);
		List<BoardDTO> list= boardService.getBoardList();
		model.addAttribute("list",list);
		articleService.updateHitCount(articleId);
		ArticleDTO article =articleService.readArticle(articleId);
		model.addAttribute("article",article);
		return "board/read";
	}
	
	//수정 화면
	@GetMapping("/{id}/modify/{articleId}")
	public String modifyPage(@PathVariable int id,@PathVariable int articleId, Model model) {
		BoardDTO board=boardService.findByBoardId(id);
		model.addAttribute("board",board);
		List<BoardDTO>list=boardService.getBoardList();
		model.addAttribute("list",list);
		ArticleDTO article = articleService.readArticle(articleId);
		model.addAttribute("article",article);
		return "board/modify";
	}
	
	@PostMapping("/{id}/modify/{articleId}")
	public String modify(@PathVariable int id, @ModelAttribute ArticleDTO article,Model model) {
		BoardDTO board= boardService.findByBoardId(id);
		model.addAttribute("board",board);
		articleService.update(article, id);
		model.addAttribute("article",article);
		return "board/read";
	}
	
	
}
