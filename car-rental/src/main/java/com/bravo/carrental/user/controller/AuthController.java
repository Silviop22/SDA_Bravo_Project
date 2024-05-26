package com.bravo.carrental.user.controller;

import com.bravo.carrental.user.dto.SignupRequest;
import com.bravo.carrental.user.dto.UserDto;
import com.bravo.carrental.user.services.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServices authServices;

@PostMapping("/signup")
    private ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
    if (authServices.validateCustomerWithEmail(signupRequest.getEmail()))
        return new ResponseEntity<>("Customer already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authServices.createCustomer(signupRequest);
        if (createdCustomerDto == null) return new ResponseEntity<>("Customer not created",
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);}

}