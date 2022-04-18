package com.kodality.controllers;

import com.kodality.dtos.TravelLog;
import com.kodality.dtos.User;
import com.kodality.dtos.UserLogSearchCriteria;
import com.kodality.services.TravelLogService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller to handle API requests
 */
@Controller("/api")
public class TravelLogController {

  @Inject
  private TravelLogService travelLogService;

  @Get(value = "travel-logs")
  public HttpResponse<List<User>> getUserTravelLogs() {
    return HttpResponse.ok().body(travelLogService.getUserTravelLog());
  }

  @Get(value = "single-travel-log")
  public HttpResponse<TravelLog> getUserData(@QueryValue("historyid") Long travelLogId) {
    return HttpResponse.ok().body(travelLogService.getTravelLogDetails(travelLogId));
  }

  @Post(value = "travel-log-search")
  public HttpResponse<List<User>> searchUserLogs(@Body UserLogSearchCriteria searchCriteria) {
    return HttpResponse.ok().body(travelLogService.searchUserByCriteria(searchCriteria));
  }

  @Delete(value = "delete-travel-log")
  public HttpResponse<Void> deleteTravelLogData(@QueryValue("travellog") Long travelLogId) {
    this.travelLogService.deleteTravelLogData(travelLogId);
    return HttpResponse.noContent();
  }

  @Put(value = "edit-travel-log")
  public HttpResponse<Void> updateTravelLogData(@Valid @Body TravelLog travelLog) throws Exception {
    this.travelLogService.updateTravelLog(travelLog);
    return HttpResponse.noContent();
  }

  @Post(value = "add-travel-log")
  public HttpResponse<Void> addTravelLog(@Valid @Body TravelLog travelLog) throws Exception {
    travelLogService.addNewTravelLog(travelLog);
    return HttpResponse.noContent();
  }
}
