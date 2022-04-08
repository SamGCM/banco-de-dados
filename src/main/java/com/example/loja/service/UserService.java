package com.example.loja.service;

import com.example.loja.Exception.InvalidValueFieldException;
import com.example.loja.dto.UserDTO;
import com.example.loja.models.Authority;
import com.example.loja.models.User;
import com.example.loja.repository.AuthorityRepository;
import com.example.loja.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDTO userDTO) {

        if (userDTO.getPassword() == null) {
            throw new InvalidValueFieldException();
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = User.convert(userDTO);

        final User userDB = userRepository.save(user);

        userDTO.getRoles()
                .forEach(a -> saveAuthority(userDB, a));
    }

    public void saveAuthority(User user, String role) {
        Authority authority = Authority.convert(user, "ROLE_" + role);
        authorityRepository.save(authority);
    }
}
