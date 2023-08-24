package com.ezen.springmvc.domain.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ezen.springmvc.domain.member.dto.Member;

public class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		return   Member
				.builder()
				.id(id)
				.name(name)
				.email(email)
				.build();
	}
}
