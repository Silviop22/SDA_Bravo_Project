package com.bravo.carrental.auth.api.service;

import com.bravo.carrental.auth.api.mapper.UserMapper;
import com.bravo.carrental.auth.api.repository.UserRepository;
import com.bravo.carrental.auth.api.model.User;
import com.bravo.carrental.auth.api.model.UserDto;
import com.bravo.carrental.car.service.ObjectPatcher;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto).toList();
    }

    @Transactional
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Transactional
    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Transactional
    public UserDto create(UserDto userDto) {

        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Transactional
    public void update(UserDto itemDto, Long id) {
        User existing = getExistingEntity(id);
        User updateCandidate = userMapper.toEntity(itemDto);
        ObjectPatcher.patchObject(updateCandidate, existing);
        userRepository.save(existing);
    }

    @Transactional
    public void deleteById(Long id) {
        getExistingEntity(id);
        userRepository.deleteById(id);
    }

    private User getExistingEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
