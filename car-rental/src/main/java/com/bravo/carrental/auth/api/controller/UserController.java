package com.bravo.carrental.auth.api.controller;

import com.bravo.carrental.auth.api.model.UserDto;
import com.bravo.carrental.auth.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserDto>> getItems() {
        logger.info("Fetching all users");
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getItemById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto userDto) {
        logger.info("Creating user with email: {}", userDto.getEmail());
        UserDto result = userService.create(userDto);
        return ResponseEntity.created(URI.create("/users/" + result.getId())).build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UserDto userDto,
                                       @PathVariable Long id) {
        logger.info("Updating user with ID: {}", id);
        userService.update(userDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
