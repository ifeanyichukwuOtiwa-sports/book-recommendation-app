package com.gxstar.bookrecommendation.security.filter;

import com.gxstar.bookrecommendation.security.jwt.JWTUtils;
import com.gxstar.bookrecommendation.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JWTUtils jWTUtils;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(final @NonNull HttpServletRequest request,
                                    final @NonNull HttpServletResponse response,
                                    final @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String jwtFromCookies = jWTUtils.getJwtFromCookies(request);
        if (jwtFromCookies == null) {
            log.error("cookies not found");
            return;
        }
        final boolean validated = jWTUtils.validateJwtToken(jwtFromCookies);

        if (!validated) {
            log.error("Invalid JWT token");
            return;
        }

        final String username = jWTUtils.generateUsernameFromToken(jwtFromCookies);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
