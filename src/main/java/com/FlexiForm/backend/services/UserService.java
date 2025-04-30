package com.FlexiForm.backend.services;

import com.FlexiForm.backend.dto.LoginResponseDTO;
import com.FlexiForm.backend.dto.SignUpDTO;
import com.FlexiForm.backend.dto.UserDTO;
import com.FlexiForm.backend.entities.UserEntity;
import com.FlexiForm.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new AuthenticationException("User with email: "
        + username + "Not Found") {});
    }


    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exists "+ signUpDTO.getEmail());

        }
        UserEntity userEntity = modelMapper.map(signUpDTO, UserEntity.class);
        userEntity.setPassword((passwordEncoder.encode(userEntity.getPassword())));
        UserEntity userEntity1 = userRepository.save(userEntity);
        return modelMapper.map(userEntity1,UserDTO.class);
    }

    public UserEntity getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }



}
