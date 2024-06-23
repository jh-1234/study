package com.example.study.service;

import com.example.study.dto.JoinDTO;
import com.example.study.entity.User;
import com.example.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {
        if (userRepository.existsByUsername(joinDTO.getUsername())) {
            return;
        }

        User user = new User();
        user.setName(joinDTO.getName());
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        user.setRole("ROLE_USER");
//        user.setRole("ROLE_MANAGER");
//        user.setRole("ROLE_ADMIN");

        userRepository.save(user);
    }
}
