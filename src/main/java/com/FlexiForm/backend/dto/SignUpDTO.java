package com.FlexiForm.backend.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
