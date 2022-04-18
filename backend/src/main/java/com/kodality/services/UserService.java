package com.kodality.services;

import com.kodality.dtos.User;
import com.kodality.mappers.UserMapper;
import com.kodality.repositories.UserRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    /**
     *
     * @return all user data without travel log data
     */
    public List<User> getAllUsers() {
        return userMapper.toUserDto(userRepository.getAllUsers());
    }
}
