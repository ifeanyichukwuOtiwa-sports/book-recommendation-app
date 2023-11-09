package com.gxstar.bookrecommendation.service.dto;

public record UserDetailsDto(
        String username,
        String password,
        String role
) {
}
