package com.kodality.rowmappers;

import com.kodality.entities.TravelLogEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravelHistoryLogMapper implements RowMapper<List<TravelLogEntity>> {

    private List<TravelLogEntity> travelHistory = new ArrayList<>();

    @Override
    public List<TravelLogEntity> mapRow(ResultSet travelHistoryResultSet, int rowNum) throws SQLException {

        travelHistory.add(new TravelLogEntity(travelHistoryResultSet.getLong("travel_log_id"),
                travelHistoryResultSet.getLong("user_id"),
                travelHistoryResultSet.getDate("logdate"),
                travelHistoryResultSet.getString("vehicle_reg_number"),
                travelHistoryResultSet.getLong("vehicle_odometer_start"),
                travelHistoryResultSet.getLong("vehicle_odometer_end"),
                travelHistoryResultSet.getLong("vehicle_odometer_end") -
                        travelHistoryResultSet.getLong("vehicle_odometer_start"),
                travelHistoryResultSet.getString("travel_route"),
                travelHistoryResultSet.getString("travel_short_description")));

        return travelHistory;
    }
}
