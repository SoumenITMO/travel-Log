package com.kodality.services;

import com.kodality.dtos.TravelLog;
import com.kodality.dtos.User;
import com.kodality.dtos.UserLogSearchCriteria;
import com.kodality.entities.TravelLogEntity;
import com.kodality.mappers.TravelLogMapper;
import com.kodality.mappers.UserMapper;
import com.kodality.repositories.TravelLogRepository;
import com.kodality.repositories.UserRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Service class to process travel log data
 */
@Singleton
public class TravelLogService {

  @Inject
  private UserMapper userMapper;

  @Inject
  private TravelLogMapper travelLogMapper;

  @Inject
  private UserRepository userRepository;

  @Inject
  private TravelLogRepository travelLogRepository;

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M.dd.yyyy");

  /**
   *
   * @param travelLog requested travel log to store
   */
  public void addNewTravelLog(TravelLog travelLog) throws Exception {

    if(travelLog.getTravelShortDescription().length() > 100) {
      throw new Exception("Travel log short description must be less than 100 charecters.");
    }

    if(travelLog.getTravelRoute().length() > 60) {
      throw new Exception("Travel log route description must be less than 60 charecters.");
    }

    if((simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime() - travelLog.getLogdate().getTime()) < 0) {
      throw new Exception("Travel log date can not be greater than today's date");
    }

    if(!userRepository.userExists(travelLog.getUserId()).booleanValue()) {
      throw new Exception("User #" + travelLog.getUserId() + " does not exists");
    }

    if((travelLog.getVehicleOdometerEnd() - travelLog.getVehicleOdometerStart()) < 0) {
      throw new Exception("Wrong odometer value has been provided.");
    }

    TravelLogEntity travelLogEntity = travelLogMapper.toTravelLogEntity(travelLog);
    travelLogRepository.createNewTravelLog(travelLogEntity);
  }

  /**
   *
   * @param searchCriteria requested search criteria values to search travel log
   * @return user list with travel log
   */
  public List<User> searchUserByCriteria(UserLogSearchCriteria searchCriteria) {
    return userMapper.toUserDto(userRepository.searchUserLogs(searchCriteria));
  }

  /**
   *
   * @param travellogId to delete travel log
   */
  public void deleteTravelLogData(Long travellogId) {
    travelLogRepository.deleteTravellog(travellogId);
  }

  /**
   *
   * @param travelLog requested travel log for update
   */
  public void updateTravelLog(TravelLog travelLog) throws Exception {

    if(travelLog.getTravelShortDescription().length() > 100) {
      throw new Exception("Travel log short description must be less than 100 charecters.");
    }

    if(travelLog.getTravelRoute().length() > 60) {
      throw new Exception("Travel log route description must be less than 60 charecters.");
    }

    if((simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime() - travelLog.getLogdate().getTime()) < 0) {
      throw new Exception("Travel log date can not be greater than today's date");
    }

    if((travelLog.getVehicleOdometerEnd() - travelLog.getVehicleOdometerStart()) < 0) {
      throw new Exception("Wrong odometer value has been provided.");
    }

    travelLogRepository.updateTravelLog(travelLogMapper.toTravelLogEntity(travelLog));
  }

  /**
   *
   * @return list of users with their travel log data
   */
  public List<User> getUserTravelLog() {
    return userMapper.toUserDto(userRepository.getTravelLogs());
  }

  /**
   *
   * @param travelLogId
   * @return
   */
  public TravelLog getTravelLogDetails(Long travelLogId) {
    return travelLogMapper.toTravelLogDto(travelLogRepository.getTravelLogDataById(travelLogId));
  }
}
