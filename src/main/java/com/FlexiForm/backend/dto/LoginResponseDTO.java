package com.FlexiForm.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDTO {
    private Long id;
    private String accessToken;
    private String refreshToken;
}
