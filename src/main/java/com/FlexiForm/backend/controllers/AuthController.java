package com.FlexiForm.backend.controllers;


import com.FlexiForm.backend.dto.LoginDTO;
import com.FlexiForm.backend.dto.LoginResponseDTO;
import com.FlexiForm.backend.dto.SignUpDTO;
import com.FlexiForm.backend.dto.UserDTO;
import com.FlexiForm.backend.services.AuthService;
import com.FlexiForm.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO){
        return userService.signUp(signUpDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO Login(@RequestBody LoginDTO loginDTO){
        return authService.login(loginDTO);
    }
}
