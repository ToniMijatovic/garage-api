package com.tonioostblok.garageapi.filters;

import com.tonioostblok.garageapi.entities.User;
import com.tonioostblok.garageapi.services.UserService;
import com.tonioostblok.garageapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

        @Autowired
        private UserService userService;

        @Autowired
        private JwtUtil jwtUtil;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

            final String authorizationHeader = request.getHeader("Authorization");

            String userId = null;
            String jwt = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                userId = jwtUtil.extractUserId(jwt);
            }

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                User userDetails = this.userService.getUser(Integer.parseInt(userId));

                if (jwtUtil.validateToken(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    request.setAttribute("user_id", userId);
                }
            }
            chain.doFilter(request, response);
        }
}
