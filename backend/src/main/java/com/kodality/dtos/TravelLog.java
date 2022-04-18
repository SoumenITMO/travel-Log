package com.kodality.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Introspected
public class TravelLog {

    private Long id;

    @NotNull(message = "User id can not null.")
    private Long userId;

    @JsonFormat(pattern = "M.dd.yyyy", timezone = "Europe/Tallinn")
    private Date logdate;

    @Pattern(regexp = "^[A-Z0-9]+$", message = "Vehicle registration number can be only alpha numeric.")
    @NotNull(message = "Vehicle registration number can not be null.")
    private String vehicleRegNumber;

    @Max(999999)
    @NotNull(message = "Vehicle odometer either null or invalid.")
    private Long vehicleOdometerStart;

    @Max(999999)
    @NotNull(message = "Vehicle odometer either null or invalid.")
    private Long vehicleOdometerEnd;

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Travel route can be only alphabets.")
    @NotNull(message = "Travel route value can not be null")
    private String travelRoute;

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Travel short description can be only alphabets.")
    @NotNull(message = "Travel short description can not be null")
    private String travelShortDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLogdate() {
        return logdate;
    }

    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public Long getVehicleOdometerStart() {
        return vehicleOdometerStart;
    }

    public void setVehicleOdometerStart(Long vehicleOdometerStart) {
        this.vehicleOdometerStart = vehicleOdometerStart;
    }

    public Long getVehicleOdometerEnd() {
        return vehicleOdometerEnd;
    }

    public void setVehicleOdometerEnd(Long vehicleOdometerEnd) {
        this.vehicleOdometerEnd = vehicleOdometerEnd;
    }

    public String getTravelRoute() {
        return travelRoute;
    }

    public void setTravelRoute(String travelRoute) {
        this.travelRoute = travelRoute;
    }

    public String getTravelShortDescription() {
        return travelShortDescription;
    }

    public void setTravelShortDescription(String travelShortDescription) {
        this.travelShortDescription = travelShortDescription;
    }
}
