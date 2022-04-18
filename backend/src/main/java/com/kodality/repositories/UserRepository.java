package com.kodality.repositories;

import com.kodality.dtos.UserLogSearchCriteria;
import com.kodality.entities.UserEntity;
import com.kodality.helper.DataFilter;
import com.kodality.rowmappers.OnlyUserRowsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * This is User repository to handle data from user table and related data from travel log
 */
@Singleton
public class UserRepository {

    @Inject
    DataFilter dataFilter;

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * @param searchCriteria given search param for travel log data
     * @return travel log data including user data
     */
    public List<UserEntity> searchUserLogs(UserLogSearchCriteria searchCriteria) {
        String query;

        String vehicleRegNumber = searchCriteria.getVehicleRegNumber() == null ? "IS NOT NULL" : "Like '%" + searchCriteria.getVehicleRegNumber().toLowerCase() + "%'";
        String vehicleOwnerName = searchCriteria.getVehicleOwnerName() == null ? "IS NOT NULL" : "Like '%" + searchCriteria.getVehicleOwnerName().toLowerCase() + "%'";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if(searchCriteria.getLogStartDate() != null && searchCriteria.getLogEndDate() != null) {
            query = "SELECT * FROM users LEFT JOIN travellog on users.id = travellog.user_id where " +
                    "(Lower(travellog.vehicle_reg_number) " + vehicleRegNumber + ") and (Lower(users.name)  " + vehicleOwnerName + ") and " +
                    "(travellog.logdate between '" + dateFormat.format(searchCriteria.getLogStartDate()) + "' and '" +
                    dateFormat.format(searchCriteria.getLogEndDate()) + "') ORDER BY travellog.logdate ASC";
        } else {
            query = "SELECT * FROM users LEFT JOIN travellog on users.id = travellog.user_id where " +
                    "(Lower(travellog.vehicle_reg_number) " + vehicleRegNumber + ") and (Lower(users.name)  " + vehicleOwnerName + ") ORDER BY travellog.logdate ASC";
        }

        return dataFilter.fetchData(jdbcTemplate, query);
    }

    /**
     *
     * @return couple of user's travel log with user data
     */
    public List<UserEntity> getTravelLogs() {
        String query = "SELECT * FROM users LEFT JOIN travellog on users.id = travellog.user_id ORDER BY travellog.logdate ASC";
        return dataFilter.fetchData(jdbcTemplate, query);
    }

    /**
     *
     * @param userId requested user id to store travel log, to check if the user exists
     * @return Boolean
     */
    public Boolean userExists(Long userId) {
        return this.jdbcTemplate.queryForObject("SELECT count(*) FROM users where id = " + userId, Integer.class) != 0;
    }

    /**
     *
     * @return list of user but only contain user data which is exclude from travel log data
     */
    public List<UserEntity> getAllUsers() {
        String query = "SELECT * FROM users;";
        return this.jdbcTemplate.query(query, new OnlyUserRowsMapper());
    }
}
