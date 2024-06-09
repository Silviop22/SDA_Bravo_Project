package com.bravo.carrental.auth.api.service;

import com.bravo.carrental.auth.api.model.AuthenticationResponse;
import com.bravo.carrental.auth.api.model.LoginRequest;
import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.auth.api.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDto request) {
        User user = userService.registerCustomer(request);
        String jwtToken = jwtService.generateToken((UserDetails) user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken usr = new UsernamePasswordAuthenticationToken(request.userName(),
                request.password());
        authenticationManager.authenticate(usr);
        User user = (User) userRepository.findFirstByEmail(request.userName()).orElseThrow(RuntimeException::new);
        String jwtToken = jwtService.generateToken((UserDetails) user);
        return new AuthenticationResponse(jwtToken);}
}