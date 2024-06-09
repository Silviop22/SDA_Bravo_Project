package com.bravo.carrental.auth.api.service;

import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.auth.api.model.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User registerCustomer(UserDto request);
    UserDetails loadUserByUsername(String userEmail);
}
