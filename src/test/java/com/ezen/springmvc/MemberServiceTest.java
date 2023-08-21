package com.ezen.springmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.member.dto.Member;
import com.ezen.springmvc.domain.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberServiceTest {
	
	@Autowired
	private MemberService memberService;
	
	@Test
	public void isMemberTest() {
		String id = "bangry", passwd = "1111";
		Member loginMember =memberService.isMember(id, passwd);
		log.info("로그인 사용자 정보 : {}", loginMember);
	}
}
