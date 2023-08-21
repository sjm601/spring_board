package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.web.PageParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
	
	private final ArticleMapper articleMapper;
	
	@Override
	@Transactional
	public void register(ArticleDTO ariArticleDTO) {
		articleMapper.create(ariArticleDTO);
		
	}

	@Override
	public int getCountAll(int boardId, String keyword) {
		return articleMapper.getCountAll(boardId, keyword);
	}

	@Override
	public List<ArticleDTO> findByAll(PageParams pageParams) {
		return articleMapper.findByAll(pageParams);
	
	}

	@Override
	public ArticleDTO findArticle(int articleId) {
		return articleMapper.findArticle(articleId);
		
	}

	@Override
	@Transactional
	public void updateOrderNo(ArticleDTO articleDTO) {
		articleMapper.updateOrderNo(articleDTO);
		
	}

	@Override
	@Transactional
	public void createReply(ArticleDTO articleDTO, int articleId) {
		articleMapper.createReply(articleDTO, articleId);
		
	}

	@Override
	@Transactional
	public void updateHitCount(int articleId) {
		articleMapper.updateHitCount(articleId);
		
	}

	@Override
	public ArticleDTO readArticle(int articleId) {
		return articleMapper.readArticle(articleId);		
	}

	@Override
	@Transactional
	public void update(ArticleDTO articleDTO, int updateArticleId) {
		articleMapper.update(articleDTO, updateArticleId);
		
	}

	@Override
	@Transactional
	public void delete(int deleteArticleId, String loginId, String passwd) {
		articleMapper.delete(deleteArticleId, loginId, passwd);
		
	}

	@Override
	@Transactional
	public void createNestedReply(ArticleDTO articleDTO, int articleId) {
		articleMapper.createNestedReply(articleDTO, articleId);
		
	}
	
}
