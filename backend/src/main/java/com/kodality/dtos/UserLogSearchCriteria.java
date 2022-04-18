package com.kodality.dtos;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserLogSearchCriteria {

    private String vehicleRegNumber;
    private String vehicleOwnerName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date logStartDate;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date logEndDate;

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public Date getLogStartDate() {
        return logStartDate;
    }

    public void setLogStartDate(Date logStartDate) {
        this.logStartDate = logStartDate;
    }

    public Date getLogEndDate() {
        return logEndDate;
    }

    public void setLogEndDate(Date logEndDate) {
        this.logEndDate = logEndDate;
    }
}
