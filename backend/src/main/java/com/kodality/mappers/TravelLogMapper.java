package com.kodality.mappers;

import com.kodality.dtos.TravelLog;
import com.kodality.entities.TravelLogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface TravelLogMapper {

    TravelLogEntity toTravelLogEntity(TravelLog travelLog);
    TravelLog toTravelLogDto(TravelLogEntity travelLogEntity);
}
