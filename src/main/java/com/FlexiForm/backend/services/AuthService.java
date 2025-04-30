package com.FlexiForm.backend.services;

import com.FlexiForm.backend.dto.LoginDTO;
import com.FlexiForm.backend.dto.LoginResponseDTO;
import com.FlexiForm.backend.entities.UserEntity;
import com.FlexiForm.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public LoginResponseDTO login(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new LoginResponseDTO(userEntity.getId(), accessToken, refreshToken);
    }

}
