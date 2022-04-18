package com.kodality.mappers;

import com.kodality.dtos.User;
import com.kodality.entities.UserEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "jsr330")
public interface UserMapper {

    List<User> toUserDto(List<UserEntity> userEntities);
}
