package com.ezen.springmvc.web.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.service.ArticleService;
import com.ezen.springmvc.domain.board.service.BoardService;
import com.ezen.springmvc.domain.common.web.PageParams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
@Slf4j
public class ArticleController {
	
	private final BoardService boardService;
	private final ArticleService articleService;
	
	
	@PostMapping("/{id}")
	public String regist(@PathVariable int id, @ModelAttribute ArticleDTO article,Model model) {
		BoardDTO board= boardService.findByBoardId(id);
		model.addAttribute("board",board);
		article.setBoardId(id);
		articleService.register(article);
		return "redirect:/board/{id}";  
	}
	
	

	

	
	
}
