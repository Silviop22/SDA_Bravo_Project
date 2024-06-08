package com.bravo.carrental.user.services;

import com.bravo.carrental.user.model.SignupRequest;
import com.bravo.carrental.user.model.UserDto;
import com.bravo.carrental.user.model.User;
import com.bravo.carrental.user.model.userRole;
import com.bravo.carrental.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AuthServicesUser implements AuthServices {
    private final UserRepository userRepository;
    UserDto userDto = null;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(userRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        userDto.setId(createdUser.getId());
        return userDto;}

    @Override
    public UserDto createEmployee(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(userRole.EMPLOYEE);
        User createdUser = userRepository.save(user);
        userDto.setId(createdUser.getId());
        return userDto;}

    @Override
    public UserDto createAdmin(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(userRole.ADMIN);
        User createdUser = userRepository.save(user);
        userDto.setId(createdUser.getId());
        return userDto;}

    @Override
    public boolean validateCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();}
    @Override
    public boolean validateEmployeeWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();}
    @Override
    public boolean validateAdminWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();}
}
