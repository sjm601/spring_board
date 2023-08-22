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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {
	
	private final BoardService boardService;
	private final ArticleService articleService;
	
	/**
	 * 게시글 등록
	 * @param int 게시판 번호(boardId)
	 * @param ArticleDTO 게시글(articleDTO)
	 * @param Model model
	 * @return String 게시판 화면(redirect)
	 */
	@PostMapping("/{id}")
	public String regist(@PathVariable int id, @ModelAttribute ArticleDTO article,Model model) {
		BoardDTO board= boardService.findByBoardId(id);
		model.addAttribute("board",board);
		article.setBoardId(id);
		articleService.register(article);
		return "redirect:/board/{id}/1";  
	}
	
	

	/**게시글 상세 화면
	 * @param id 게시판 번호(boardId)
	 * @param int 게시글번호(articleId)
	 * @param Model model
	 * @return String 게시글 상세페이지
	 */
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
	
	/**
	 * 게시글 수정 화면 요청
	 * @param id 게시판 번호(boardId)
	 * @param int 게시글번호(articleId)
	 * @param Model model
	 * @return String 게시글 수정 화면
	 */
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
	
	/**
	 * 게시판 수정 
	 */
	@PostMapping("/{id}/modify/{articleId}")
	public String update(@PathVariable int id, @PathVariable int articleId, @ModelAttribute ArticleDTO articleDTO,
			Model model) {
		BoardDTO board = boardService.findByBoardId(id);
		model.addAttribute("board", board);
		List<BoardDTO> list = boardService.getBoardList();
		model.addAttribute("list", list);
		
		ArticleDTO article = articleService.find(articleId);
		model.addAttribute("article", article);
		String passwd = article.getPasswd();
		String inputPasswd = articleDTO.getPasswd();
		String subject = articleDTO.getSubject();
		String content = articleDTO.getContent();
		log.info("입력값 : {}", articleDTO);
		article.setSubject(subject);
		article.setContent(content);
		log.info("업데이트 : {}", article);

		if (passwd.equals(inputPasswd)) {
			articleService.update(article);
			return "redirect:/article/{id}/read/{articleId}";
		} else {
			return "board/error";
		}
	}
	
	/**
	 * 게시글 삭제
	 * @param id 게시판 번호(boardId)
	 * @param int 게시글번호(articleId)
	 * @param ArticleDTO 게시글(articleDTO)
	 * @param Model model
	 * @return String 게시판 화면(redirect)(성공)
	 * @return String 오류 페이지(실패)
	 */
	@PostMapping("/{id}/delete/{articleId}")
	public String delete(@PathVariable int id, @PathVariable int articleId, @ModelAttribute ArticleDTO articleDTO,
			Model model) {
		BoardDTO board = boardService.findByBoardId(id);
		model.addAttribute("board", board);
		List<BoardDTO> list = boardService.getBoardList();
		model.addAttribute("list", list);
		
		ArticleDTO article = articleService.find(articleId);
		model.addAttribute("article", article);
		String passwd =article.getPasswd();
		String inputPasswd = articleDTO.getPasswd();
		if (passwd.equals(inputPasswd)) {
			articleService.delete(articleId);
			return "redirect:/board/{id}/1";
		} else {
			return "board/error";
		}
	}
	
	/**
	 * 게시글 답글 화면 요청
	 * @param int 게시판 번호(boardId)
	 * @param ArticleDTO 게시글(articleDTO)
	 * @param Model model
	 * @return String 답글 화면
	 */
	@GetMapping("{id}/reply/{articleId}")
	public String replyView(@PathVariable int id,@PathVariable int articleId, Model model) {
		BoardDTO board = boardService.findByBoardId(id);
		model.addAttribute("board", board);
		List<BoardDTO> list = boardService.getBoardList();
		model.addAttribute("list", list);
		
		model.addAttribute("topArticleId",articleId);
		return "board/replyRegister";
	}
	
	@PostMapping("{id}/reply/{articleId}")
	public String replyRegist(@PathVariable int id,@PathVariable int articleId,@ModelAttribute ArticleDTO articleDTO, Model model) {
		BoardDTO board = boardService.findByBoardId(id);
		model.addAttribute("board", board);
		List<BoardDTO> list = boardService.getBoardList();
		model.addAttribute("list", list);
		
		articleDTO.setBoardId(id);
		ArticleDTO topArticle= articleService.find(articleId);
		articleService.createReply(articleDTO,topArticle);
		
		return "redirect:/board/{id}/1";
	}
}
