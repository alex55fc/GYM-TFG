package com.gym.tfg.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserGymClassDtoRowMapper implements RowMapper<UserGymClassDto>{

	@Override
	public UserGymClassDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserGymClassDto dto = new UserGymClassDto();
		dto.setEmailUser(rs.getString("user_email"));
		dto.setGymClassId(rs.getInt("gym_class_id"));
		return dto;
	}



}
