package com.ezen.springmvc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Spring Mybatis 사용을 위한 멤버 DTO
 * @author 박상훈
 *
 */

@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Member {
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String regdate;
	
}
