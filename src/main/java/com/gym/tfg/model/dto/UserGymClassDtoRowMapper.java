package com.gym.tfg.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper implementation for mapping rows of a ResultSet to UserGymClassDto objects.
 */
public class UserGymClassDtoRowMapper implements RowMapper<UserGymClassDto>{

	@Override
	public UserGymClassDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserGymClassDto dto = new UserGymClassDto();
		dto.setEmailUser(rs.getString("user_email"));
		dto.setGymClassId(rs.getInt("gym_class_id"));
		dto.setGymClassName(rs.getString("name_class"));
		dto.setGymClassWeeklyDay(rs.getString("weekly_day"));
		return dto;
	}



}
