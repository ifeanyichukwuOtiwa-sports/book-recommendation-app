package com.gxstar.bookrecommendation.repository.api.dto;

public record UserDto(
        String firstName,
        String lastName,
        String username,
        String email,
        String role
) {
}
