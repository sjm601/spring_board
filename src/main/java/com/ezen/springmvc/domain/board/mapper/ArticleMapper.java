package com.ezen.springmvc.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.PageParams;

/**
 * 게시판 게시글 테이블 관련 명세
 * @author 박상훈
 */
@Mapper
public interface ArticleMapper {
	
	/** 신규 게시글 등록 */
	public void create(ArticleDTO articleDTO);
	
	/** 게시글 찾기 */
	public ArticleDTO find(int articleId);
	
	/** 페이징 계산(검색값 포함)에 필요한 게시글 전체 갯수 반환 */
	public int getCountAll(@Param("boardId")int boardId, @Param("keyword")String keyword);
	
	/** 요청 페이지, 페이지당 보여지는 목록 갯수에 따른 목록 반환 */
	public List<ArticleDTO> findByAll(PageParams pageParams);
	
	/** 댓글, 대댓글 등록 기능을 위한 부모 게시글 검색 */
	public ArticleDTO findArticle(int articleId);
	
	/** 대댓글 등록 전 orderNo 업데이트 */
	public void updateOrderNo(ArticleDTO articleDTO);
	
	/** 댓글 등록 */
	public void createReply(@Param("articleDTO")ArticleDTO articleDTO, @Param("topArticle")ArticleDTO topArticle);
	
	/** 대댓글 등록 */
	public void createNestedReply(@Param("articleDTO")ArticleDTO articleDTO, @Param("topArticle")ArticleDTO topArticle);
	
	/** 게시글 상세보기 시 조회수 갱신 */
	public void updateHitCount(int articleId);
	
	/** 게시글 상세보기 */
	public ArticleDTO readArticle(int articleId);
	
	/** 게시글 업데이트 */
	public void update(ArticleDTO articleDTO);
	
	/** 게시글 삭제 */
	public void delete(ArticleDTO articleDTO);
}