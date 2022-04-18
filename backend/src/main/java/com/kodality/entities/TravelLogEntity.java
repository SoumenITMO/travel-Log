package com.kodality.entities;

import java.util.Date;

public class TravelLogEntity {

  private Long id;
  private Long userId;
  private Date logdate;
  private String vehicleRegNumber;
  private Long vehicleOdometerStart;
  private Long vehicleOdometerEnd;
  private Long actualOdoMeterValue;
  private String travelRoute;
  private String travelShortDescription;

  public TravelLogEntity(Long id, Long userId, Date logdate, String vehicleRegNumber, Long vehicleOdometerStart,
                         Long vehicleOdometerEnd, Long actualOdoMeterValue, String travelRoute, String travelShortDescription) {
    this.id = id;
    this.userId = userId;
    this.logdate = logdate;
    this.vehicleRegNumber = vehicleRegNumber;
    this.vehicleOdometerStart = vehicleOdometerStart;
    this.vehicleOdometerEnd = vehicleOdometerEnd;
    this.actualOdoMeterValue = actualOdoMeterValue;
    this.travelRoute = travelRoute;
    this.travelShortDescription = travelShortDescription;
  }

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

  public Long getActualOdoMeterValue() {
    return actualOdoMeterValue;
  }

  public void setActualOdoMeterValue(Long actualOdoMeterValue) {
    this.actualOdoMeterValue = actualOdoMeterValue;
  }
}
