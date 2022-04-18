package com.kodality.rowmappers;

import com.kodality.entities.TravelLogEntity;
import com.kodality.entities.UserEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<UserEntity> {

    private UserEntity user;
    private List<Long> userAdded = new ArrayList<>();
    private final TravelHistoryLogMapper travelHistoryLogMapper = new TravelHistoryLogMapper();

    @Override
    public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        if(!userAdded.contains(resultSet.getLong("id"))) {
            user = new UserEntity();
            this.user.setId(resultSet.getLong("id"));
            this.user.setName(resultSet.getString("name"));
            this.user.setAge(resultSet.getInt("age"));
            this.user.setAddress(resultSet.getString("address"));
            userAdded.add(resultSet.getLong("id"));
        }

        List<TravelLogEntity> travelHistory = travelHistoryLogMapper.mapRow(resultSet, -1);

        user.setLogs(travelHistory);
        return this.user;
    }
}
