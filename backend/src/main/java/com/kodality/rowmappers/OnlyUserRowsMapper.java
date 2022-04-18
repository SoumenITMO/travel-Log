package com.kodality.rowmappers;

import com.kodality.entities.UserEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is RowMapper class and it is responsible to map only user table fields
 */
public class OnlyUserRowsMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setAddress(resultSet.getString("address"));
        return user;
    }
}
