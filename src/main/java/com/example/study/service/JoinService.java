package com.example.study.service;

import com.example.study.constant.UserRole;
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

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void joinProcess(JoinDTO joinDTO) {
        if (existsByUsername(joinDTO.getUsername())) {
            return;
        }

        User user = new User();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        user.setGender(joinDTO.getGender());
        user.setBirthDay(joinDTO.getBirthDay());
        user.setPhone(joinDTO.getPhone());
        user.setRole(UserRole.ROLE_USER);

        userRepository.save(user);
    }
}
