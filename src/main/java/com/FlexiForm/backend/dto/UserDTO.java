package com.FlexiForm.backend.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
