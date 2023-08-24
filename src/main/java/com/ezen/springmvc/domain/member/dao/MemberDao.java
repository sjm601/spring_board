package com.ezen.springmvc.domain.member.dao;

import java.util.List;

import com.ezen.springmvc.domain.member.dto.Member;

public interface MemberDao {
	
	public int getCount();
	public List<Member> findByAll();
	public Member findById(String id);

}
