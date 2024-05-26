package com.bravo.carrental.user.services;

import com.bravo.carrental.user.dto.SignupRequest;
import com.bravo.carrental.user.dto.UserDto;

public interface AuthServices{
    UserDto createCustomer(SignupRequest signupRequest);
    boolean validateCustomerWithEmail(String email);
}
