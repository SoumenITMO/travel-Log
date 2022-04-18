package com.kodality.helper;

import com.kodality.entities.TravelLogEntity;
import com.kodality.entities.UserEntity;
import com.kodality.rowmappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DataFilter {

    private double totalDistanceTraveled;

    /**
     *
     * @param jdbcTemplate
     * @param query
     * @return
     */
    public List<UserEntity> fetchData(JdbcTemplate jdbcTemplate, String query) {
        List<UserEntity> userData = jdbcTemplate.query(query, new UserRowMapper())
                .stream().distinct().collect(Collectors.toList());

        List<UserEntity> filteredUserTravelLogData = new ArrayList<>();

        userData.forEach(getUserData -> {

            totalDistanceTraveled = getUserData.logs.stream()
                    .filter(filterLogByUserId -> filterLogByUserId.getUserId().equals(getUserData.getId()))
                    .map(TravelLogEntity::getActualOdoMeterValue)
                    .mapToInt(m -> (int) m.doubleValue()).sum();

            filteredUserTravelLogData.add(
                    new UserEntity(getUserData.getId(),
                            getUserData.getName(),
                            getUserData.getAge(),
                            getUserData.getAddress(),
                            String.valueOf(Math.round(totalDistanceTraveled)).concat(" Km."),
                            getUserData.logs.stream()
                                    .filter(filterLogByUserId -> filterLogByUserId.getUserId().equals(getUserData.getId()))
                                    .collect(Collectors.toList()))
            );
        });
        return filteredUserTravelLogData;
    }
}
