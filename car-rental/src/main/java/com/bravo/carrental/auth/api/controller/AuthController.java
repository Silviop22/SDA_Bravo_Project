//package com.bravo.carrental.auth.api.controller;
//import com.bravo.carrental.auth.api.model.AuthenticationResponse;
//import com.bravo.carrental.auth.api.model.LoginRequest;
//import com.bravo.carrental.auth.api.model.UserDto;
//import com.bravo.carrental.auth.api.service.AuthService;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//
//public class AuthController {
//    private final AuthService authService;
//    private UserDto request;
//
//    @PostMapping("/signup")
//    public ResponseEntity<AuthenticationResponse> signupCustomer(@RequestBody UserDto request) {
//        this.request = request;
////        AuthenticationResponse response = authService.register(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
//        return ResponseEntity.ok(authService.login(request));
//    }
//
//}