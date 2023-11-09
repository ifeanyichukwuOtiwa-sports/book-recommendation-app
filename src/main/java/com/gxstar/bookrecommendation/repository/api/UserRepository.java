package com.gxstar.bookrecommendation.repository.api;

import com.gxstar.bookrecommendation.model.User;
import com.gxstar.bookrecommendation.repository.api.dto.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<UserDto> findByUsername(String username);
    Optional<UserDto> findByUserNameAndPassword(String username, String password);
    UUID save(UserDto user);
    UserDto findByUserUuid(UUID userUuid);
    boolean updateByUsername(String username, String firstName, String lastName, String email);
    boolean deleteByUsername(String username);
    boolean deleteById(UUID id);
}
