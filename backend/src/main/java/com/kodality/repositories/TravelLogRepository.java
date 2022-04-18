package com.kodality.repositories;

import com.kodality.entities.TravelLogEntity;
import com.kodality.rowmappers.TravelSingleHistoryLogMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.inject.Singleton;

/**
 * This Repository handle only travel log data,
 */
@Singleton
public class TravelLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public TravelLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * @param travelLog requested travel log data to store
     */
    public void createNewTravelLog(TravelLogEntity travelLog) {

        String travelLogInsertQuery = "INSERT INTO travellog(user_id, logdate, vehicle_reg_number, " +
                "vehicle_odometer_start, vehicle_odometer_end, travel_route, " +
                "travel_short_description, travel_log_id) values(?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(travelLogInsertQuery, travelLog.getUserId(), travelLog.getLogdate(),
                travelLog.getVehicleRegNumber(), travelLog.getVehicleOdometerStart(), travelLog.getVehicleOdometerEnd(),
                travelLog.getTravelRoute(), travelLog.getTravelShortDescription(), getTravelLogLastId() + 1);
    }

    /**
     *
     * @param travellogId requested travel log id to delete
     */
    public void deleteTravellog(Long travellogId) {
        this.jdbcTemplate.execute("DELETE FROM travellog where travel_log_id = " + travellogId);
    }

    /**
     *
     * @param travelLog requested travel log data to update
     */
    public void updateTravelLog(TravelLogEntity travelLog) {
        String updateTravelLogDataQuery = "UPDATE travellog set vehicle_reg_number = ?, vehicle_odometer_start = ?, " +
                "vehicle_odometer_end = ?, travel_route = ?, travel_short_description = ?, logdate = ? where travel_log_id = ?";

        this.jdbcTemplate.update(updateTravelLogDataQuery, travelLog.getVehicleRegNumber(), travelLog.getVehicleOdometerStart(),
                travelLog.getVehicleOdometerEnd(), travelLog.getTravelRoute(), travelLog.getTravelShortDescription(), travelLog.getLogdate(),
                travelLog.getId());
    }

    /**
     *
     * @param travelLogId requested travel id to get single travel record
     * @return
     */
    public TravelLogEntity getTravelLogDataById(Long travelLogId) {
        String getTravelLogDataQuery = "SELECT * from travellog where travel_log_id = " + travelLogId;
        return this.jdbcTemplate.query(getTravelLogDataQuery, new TravelSingleHistoryLogMapper()).get(0);
    }

    /**
     *
     * @return last id of travel log table
     */
    private Long getTravelLogLastId() {
        return this.jdbcTemplate.queryForObject("SELECT travel_log_id from travellog Order By travel_log_id DESC Limit 1;", Long.class);
    }
}
