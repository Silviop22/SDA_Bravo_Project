package com.bravo.carrental.user.services;

import com.bravo.carrental.user.model.SignupRequest;
import com.bravo.carrental.user.model.UserDto;

public interface AuthServices{
    UserDto createCustomer(SignupRequest signupRequest);

    UserDto createEmployee(SignupRequest signupRequest);

    UserDto createAdmin(SignupRequest signupRequest);

    boolean validateCustomerWithEmail(String email);

    boolean validateEmployeeWithEmail(String email);

    boolean validateAdminWithEmail(String email);
}
