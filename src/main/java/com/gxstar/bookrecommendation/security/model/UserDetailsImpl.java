package com.gxstar.bookrecommendation.security.model;

import com.gxstar.bookrecommendation.model.User;
import com.gxstar.bookrecommendation.service.dto.UserDetailsDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetailsImpl of(final UserDetailsDto user) {
        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.role());
        return new UserDetailsImpl(
                user.username(),
                user.password(),
                new ArrayList<>(List.of(authority))
        );
    }
}
