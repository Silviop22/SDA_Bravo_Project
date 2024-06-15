//package com.bravo.carrental.auth.config;
//
//import com.bravo.carrental.auth.api.service.JwtTokenService;
//import com.bravo.carrental.auth.api.service.UserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@NoArgsConstructor(force = true)
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenService jwtService;
//    private final UserService userService;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull
//            HttpServletRequest request,
//            @NonNull
//            HttpServletResponse response,
//            @NonNull
//            FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;}
//
//        String jwt = authHeader.substring(7);
//        String userEmail = jwtService.extractUsername(jwt);
//        UserDetails userDetails = this.userService.loadUserByUsername(userEmail);
//        if (jwtService.isValidToken(jwt, userDetails)) {
//            authenticate(jwt, request, userEmail);}
//
//        filterChain.doFilter(request, response);}
//
//    private void authenticate(String jwt, HttpServletRequest request, String username) {
//        UserDetails userDetails = this.userService.loadUserByUsername(username);
//        if (jwtService.isValidToken(jwt, userDetails)) {
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(userDetails, null,
//                    userDetails.getAuthorities());
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authToken);}}
//}