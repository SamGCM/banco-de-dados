package com.example.loja.controller;

import com.example.loja.dto.UserDTO;
import com.example.loja.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }
}
