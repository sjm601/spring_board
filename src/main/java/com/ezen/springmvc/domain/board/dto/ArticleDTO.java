package com.ezen.springmvc.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
/**
 * 게시판 게시글 DTO 
 * @author 박상훈
 */
public class ArticleDTO {
	int articleId;		
	int boardId;		
	String writer;		
	String subject;		
	String content;		
	String regdate;		
	int hitCount;		
	String passwd;		
	int groupNo;		
	int levelNo;		
	int orderNo;		
}