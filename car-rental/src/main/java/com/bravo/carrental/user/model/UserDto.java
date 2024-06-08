package com.bravo.carrental.user.model;

import com.bravo.carrental.car.model.CarCreationValidation;
import com.bravo.carrental.car.model.CarDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserDto {
    private UserDto userDto;
    @Id
    private Long id;
    @NotBlank(message = "Name is mandatory", groups = UserCreationValidation.class)
    private String name;
    @NotBlank(message = "Email is mandatory", groups = UserCreationValidation.class)
    private String email;
    @NotBlank(message = "Password is mandatory", groups = UserCreationValidation.class)
    private String password;
    @NotBlank(message = "User role is mandatory", groups = UserCreationValidation.class)
    private Enum userRole;
}
