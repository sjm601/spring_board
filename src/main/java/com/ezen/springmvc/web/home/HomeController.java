package com.ezen.springmvc.web.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.service.BoardService;
import com.ezen.springmvc.domain.member.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class HomeController {
	
	private final BoardService boardService;
	
	@GetMapping("")
	public String home(@SessionAttribute(name="loginMember", required = false) Member loginMember,Model model) {
		log.info("게시판 목록 요청");
		if (loginMember != null) {
			model.addAttribute("member", loginMember);
		}
		List<BoardDTO> list= boardService.getBoardList();
		model.addAttribute("list",list);
		return "board/index";
	}
	

}
