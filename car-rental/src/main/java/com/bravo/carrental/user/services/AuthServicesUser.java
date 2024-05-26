package com.bravo.carrental.user.services;

import com.bravo.carrental.user.dto.SignupRequest;
import com.bravo.carrental.user.dto.UserDto;
import com.bravo.carrental.user.entity.User;
import com.bravo.carrental.user.entity.UserRole;
import com.bravo.carrental.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServicesUser implements AuthServices {
    private final UserRepository userRepository;
    public AuthServicesUser(UserRepository userRepository) {
        this.userRepository = userRepository;};

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;}

    @Override
    public boolean validateCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
