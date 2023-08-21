package com.ezen.springmvc.domain.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.member.dto.Member;
import com.ezen.springmvc.domain.member.dto.MemberSearchCondition;

/**
 * MemberMapper 
 * @author 박상훈
 */
@Mapper
//@Repository
public interface MemberMapper {
	// 전체 리스트
	public List<Member> findByAll();
	
	// 회원 아이디 조회
	public Member findById(String id);
	
	//회원 로그인 
	public Member findByIdAndPasswd(@Param("id") String id,@Param("passwd") String passwd);
	
	// 이름으로 회원 리스트 조회 (와일드카드 검색)
	public List<Member> findByNameLike(String name);
	
	// 회원 가입
	public void create(Member member);
	
	// 회원 정보 수정
	public void update(Member member);
	
	// 검색 타입별 회원 검색
	public List<Member> findBySearchType(@Param("type") String type, @Param("value") String value);
	
	// 통합 검색
	public List<Member> findBySearchAll(String value);
	
	// 통합 검색 (검색 조건에 따른 체크박스 파악 방식)
	public List<Member> findBySearchAllOption(MemberSearchCondition searchCondition);
	
}
