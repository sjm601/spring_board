package com.ezen.springmvc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 조회 시 상세 검색 조건에 따른 DTO 클래스
 * @author 박상훈
 *
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MemberSearchCondition {
	private String memberId;
	private String name;
	private String email;
	
}
