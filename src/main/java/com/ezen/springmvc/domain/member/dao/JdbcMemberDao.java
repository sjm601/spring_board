package com.ezen.springmvc.domain.member.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.springmvc.domain.member.dto.Member;

import lombok.RequiredArgsConstructor;

/**
 * Spring JDBC 활용하여 데이터베이스 연동
 */
@Repository
@RequiredArgsConstructor
public class JdbcMemberDao implements MemberDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public int getCount() {
		String sql = "SELECT count(*) from member";
		int rowCount = jdbcTemplate.queryForObject(sql, Integer.class);
		return rowCount;
	}

	@Override
	public List<Member> findByAll() {
		String sql = "SELECT id, name, email FROM member";
		//List<Member> list = jdbcTemplate.query(sql, new MemberRowMapper());
		//return list;
		
		// 람다식 활용 (Java 8 이후)
		List<Member> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			return   Member
					.builder()
					.id(id)
					.name(name)
					.email(email)
					.build();
		});
		return list;
	}

	@Override
	public Member findById(String id) {
		String sql = " SELECT id, name, email from member" +
	                 " WHERE id = ?";
		return jdbcTemplate.queryForObject(
				sql, 
				(rs, rowNum) -> {
					return   Member
							.builder()
							.id(rs.getString("id"))
							.name(rs.getString("name"))
							.email(rs.getString("email"))
							.build(); 
				}, 
				id);
	}
	
	// INSERT, UPDATE, DELETE 문의 경우  jdbcTemplate.update(sql, parameter); 사용

}
