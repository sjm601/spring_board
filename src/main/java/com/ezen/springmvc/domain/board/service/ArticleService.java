package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.PageParams;

/**
 * 게시판 게시글 관련 비즈니스 로직 처리 및 트랜잭션 관리
 * 
 * @author 박상훈
 *
 */
public interface ArticleService {
	public void register(ArticleDTO ariArticleDTO);

	public int getCountAll(@Param("boardId") int boardId, @Param("keyword") String keyword);

	public List<ArticleDTO> findByAll(PageParams pageParams);

	public ArticleDTO findArticle(int articleId);

	public void updateOrderNo(ArticleDTO articleDTO);

	public void createReply(@Param("articleDTO") ArticleDTO articleDTO, @Param("topArticleId") int articleId);

	public void createNestedReply(@Param("articleDTO") ArticleDTO articleDTO, @Param("topArticleId") int articleId);

	public void updateHitCount(int articleId);

	public ArticleDTO readArticle(int articleId);

	public void update(@Param("articleDTO") ArticleDTO articleDTO, @Param("updateArticleId") int updateArticleId);

	public void delete(@Param("deleteArticleId") int deleteArticleId, @Param("loginId") String loginId,
			@Param("passwd") String passwd);
}
