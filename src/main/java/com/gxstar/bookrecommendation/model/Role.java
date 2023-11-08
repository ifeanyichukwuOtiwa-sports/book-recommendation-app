package com.gxstar.bookrecommendation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Role {
    USER("user"),
    JURISDICTION_ADMIN("jurisdiction_admin"),
    SUPERADMIN("superadmin");

    private final String name;
}
