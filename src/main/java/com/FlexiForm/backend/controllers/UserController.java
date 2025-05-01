package com.FlexiForm.backend.controllers;

import com.FlexiForm.backend.dto.UserDTO;
import com.FlexiForm.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("{userId}")
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.getAllUser(userId);
    }


}
