package com.gxstar.bookrecommendation.security.service;

import com.gxstar.bookrecommendation.model.User;
import com.gxstar.bookrecommendation.repository.api.UserRepository;
import com.gxstar.bookrecommendation.security.model.UserDetailsImpl;
import com.gxstar.bookrecommendation.service.api.UserService;
import com.gxstar.bookrecommendation.service.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserDetailsDto> byUsername = userService.findByUsername(username);
        final UserDetailsDto user = byUsername.orElseThrow();
        return UserDetailsImpl.of(user);
    }
}
