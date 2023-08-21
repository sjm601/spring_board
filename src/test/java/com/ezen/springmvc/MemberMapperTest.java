package com.ezen.springmvc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.member.dto.Member;
import com.ezen.springmvc.domain.member.dto.MemberSearchCondition;
import com.ezen.springmvc.domain.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	@Disabled
	public void findByAllTest() {
		List<Member> list = memberMapper.findByAll();
		for (Member member : list) {
			log.info(member.toString());
		}
	}
	
	@Test
	@Disabled
	public void findByIdTest() {
		// given
		String memberId = "bangry";
		// when
		Member member = memberMapper.findById(memberId);
		// then
		assertThat(memberId)
			.isNotNull();
		log.info("회원정보 : {}", member.toString());
	}
	
	@Test
	@Disabled
	void findByNameLikeTest() {
		String findName = "박";
		List<Member> list = memberMapper.findByNameLike(findName);
		log.info("이름 와일드카드 검색 : {}", list);
		for (Member member : list) {
			log.info(member.toString());
		}
	}
	
	@Test
	@Disabled
	void createTest() {
		Member createMember = Member
				.builder()
				.id("sony")
				.passwd("1111")
				.name("손흥민")
				.email("sony@gmail.com")
				.build();
		memberMapper.create(createMember);
		log.info("회원 등록 완료 : {}", createMember);
	}
	
	@Test
	@Disabled
	void updateTest() {
		Member updateMember = Member
				.builder()
				.id("sony")
				.passwd("2222")
				.build();
		memberMapper.update(updateMember);
		log.info("회원 수정 완료 : {}", updateMember);
	}
	
	
	@Test
	@Disabled
	void findBySearchTypeTest() {
		//List<Member> list = memberMapper.findBySearchType("id", "bangry");
		List<Member> list = memberMapper.findBySearchType("name", "김");
		log.info("검색 타입별 검색 회원 전체목록 : {}", list);
	}
	
	@Test
	@Disabled
	void findBySearchAllTest() {
		//List<Member> list = memberMapper.findBySearchAll("bangry");
		List<Member> list = memberMapper.findBySearchAll("박");
		log.info("모든 검색 전체목록 : {}", list);
	}
	
	@Test
	//@Disabled
	void findBySearchAllOptionTest() {
		MemberSearchCondition searchCondition = 
				MemberSearchCondition
				.builder()
				.memberId("bangry")
				.name("박")
//				.email("bangry@gmail.com")
				.build();
		List<Member> list = memberMapper.findBySearchAllOption(searchCondition);
		log.info("검색 옵션별 전체목록 : {}", list);
		log.info("검색 수 : {}", list.size());
	}
	

}
