package com.example.patientmanager.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    DOCTOR(Arrays.asList("ROLE_DOCTOR")),
    ADMIN(Arrays.asList("ROLE_ADMIN", "ACCESS_ADMIN_PANEL"));

    private final List<String> permissions;

    Role(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
