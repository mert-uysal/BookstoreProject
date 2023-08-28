package com.example.bookstoreproject.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {

            String jwtToken = authHeader.substring(7); //bearer <token>
            if (jwtUtil.validateJwtToken(jwtToken)) {
                JwtUser jwtUser = jwtUtil.getAuthenticatedUserFromToken(jwtToken);
                UserDetails user = jwtUserDetailsService.loadUserByUsername(jwtUser.getEmail());

                if (user != null) {
                    UsernamePasswordAuthenticationToken upassToken =
                        new UsernamePasswordAuthenticationToken(user, null, null);

                    upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(upassToken);
                }
            }
        }

        filterChain.doFilter(request, response);

    }
}
