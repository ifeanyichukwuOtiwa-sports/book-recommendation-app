package com.gxstar.bookrecommendation.service.api;

import com.gxstar.bookrecommendation.service.dto.UserDetailsDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDetailsDto> findByUsername(String username);
}
