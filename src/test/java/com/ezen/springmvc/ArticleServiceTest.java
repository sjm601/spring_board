package com.ezen.springmvc;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.service.ArticleService;
import com.ezen.springmvc.domain.common.web.PageParams;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ArticleServiceTest {
	
	@Autowired
	private ArticleService articleService;
	
	@Test
	@DisplayName("게시글 등록")
	@Disabled
	public void registerTest() {
		ArticleDTO newArticle = ArticleDTO.builder()
				  .boardId(20)
				  .writer("bukayosaka")
				  .subject("아스날이 챔스 우승 가보자")
				  .content("블라호비치, 사카 배터리 인정?")
				  .passwd("1111")
				  .levelNo(0)
				  .orderNo(0)
				  .build();
		articleService.register(newArticle);
		log.info("게시글 등록 완료 : {}", newArticle);
	}
	
	@Test
	@DisplayName("페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수")
	//@Disabled
	public void getCountAllTest() {
		int boardId = 10;
		String keyword = "아스날";
		int count = articleService.getCountAll(boardId, keyword);
		log.info("게시글 전체 갯수 : {}", count);
	}
	
	@Test
	@DisplayName("페이지당 보여지는 목록 갯수에 따른 게시글 목록")
	@Disabled
	public void findByAllTest() {
		// 페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수
		int boardId = 10;
		String keyword = "아스날";
		int count = articleService.getCountAll(boardId, keyword);
		
		PageParams pageParams = PageParams.builder()
										  .elementSize(10)
										  .pageSize(10)
										  .requestPage(1)
										  .rowCount(count)
										  .boardId(10)
										  .keyword("제목")
										  .build();
		List<ArticleDTO> pagingArticleList = articleService.findByAll(pageParams);
		log.info("페이징된 게시글 리스트 : {}", pagingArticleList);
	}
	
	@Test
	@DisplayName("댓글, 대댓글 등록 기능을 위한 부모 게시글 검색")
	@Disabled
	public void findArticleTest() {
		ArticleDTO articleDTO = articleService.findArticle(1);
		log.info("부모 게시글 : {}", articleDTO);
	}
	
	@Test
	@DisplayName("댓글 등록")
	@Disabled
	public void createReplyTest() {
		// 댓글 등록 할 게시글 검색
		ArticleDTO topArticle = articleService.findArticle(1);
		// 댓글 등록 전 orderNo 업데이트
		articleService.updateOrderNo(topArticle);
		// 댓글 생성
		ArticleDTO newReply = ArticleDTO.builder()
										.boardId(10)
										.writer("rice41")
										.subject("아스날 주말 개꿀 시간 경기")
										.content("해줘야 한다 ㅇㅈ?")
										.passwd("1111")
										.groupNo(topArticle.getGroupNo())
										.levelNo(topArticle.getLevelNo())
										.build();
		articleService.createReply(newReply, topArticle.getArticleId());
		log.info("댓글 등록 완료 : {}", topArticle);
	}
	
	@Test
	@DisplayName("대댓글 등록")
	@Disabled
	public void createNestedReplyTest() {
		// 대댓글 등록 할 게시글 검색
		ArticleDTO topArticle = articleService.findArticle(12);
		// 대댓글 등록 전 orderNo 업데이트
		articleService.updateOrderNo(topArticle);
		// 대댓글 생성
		ArticleDTO newNewstedReply = ArticleDTO.builder()
											   .boardId(10)
											   .writer("monday")
											   .subject("대댓글 등록 테스트 2")
											   .content("대댓글 등록 테스트 내용")
											   .passwd("1111")
											   .groupNo(topArticle.getGroupNo())
											   .levelNo(topArticle.getLevelNo())
											   .build();
		articleService.createNestedReply(newNewstedReply, topArticle.getArticleId());
		log.info("대댓글 등록 완료 : {}", newNewstedReply);
	}
	
	@Test
	@DisplayName("게시글 상세보기")
	@Disabled
	public void readArticleTest() {
		int articleId = 42;
		// 게시글 상세보기 시 조회수 갱신
		articleService.updateHitCount(articleId);
		// 게시글 상세보기
		ArticleDTO readArticle= articleService.readArticle(articleId);
		log.info("게시글 상세보기 : {}", readArticle);
	}
	
	@Test
	@DisplayName("게시글 업데이트")
	//@Disabled
	public void updateTest() {
		int updateArticleId = 54;
		// 업데이트 게시물 생성
		ArticleDTO updateArticle = ArticleDTO.builder()
//											 .boardId(20)
											 .subject("아스날화이티잉123")
//											 .content("내용 수정 1")
					//						 .passwd("1234")
											 .build();
		articleService.update(updateArticle, updateArticleId);
		log.info("게시글 수정 완료 : {}", updateArticle);
	}
	
	@Test
	@DisplayName("게시글 삭제")
	@Disabled
	public void deleteTest() {
		int deleteArticleId = 17;
		String loginId = "thursday";
		String passwd = "1111";
		// 삭제할 게시글 검색
		ArticleDTO deleteArticle = articleService.findArticle(deleteArticleId);
		articleService.delete(deleteArticleId, loginId, passwd);
		log.info("게시글 삭제 완료 : {}", deleteArticle);
	}
	

	
}
