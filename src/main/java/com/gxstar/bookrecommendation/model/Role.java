package com.gxstar.bookrecommendation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
public enum Role {
    USER("user"),
    JURISDICTION_ADMIN("jurisdiction_admin"),
    SUPERADMIN("superadmin");

    private final String name;

    public static Role of(final String accessRole) {
        return Arrays.stream(Role.values())
                .filter(role -> Objects.equals(role.name, accessRole))
                .findAny().orElse(USER);
    }
}
