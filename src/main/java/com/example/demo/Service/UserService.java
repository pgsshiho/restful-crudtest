package com.example.demo.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//서비스
public class UserService {
    @Autowired//오토와이어
    UserRepository userRepository;


    public UserDTO insertUser(UserDTO user) {
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDTO> getUserByUserId(String userId) {
        return userRepository.findById(userId);
    }

    public UserDTO updatePassword(String userId, UserDTO updatedUser) {
        return userRepository.findById(userId).map(existingUser -> {
            // 기존 UserDTO 객체를 기반으로 새로운 객체를 생성하고, 비밀번호만 업데이트
            UserDTO updated = UserDTO.builder()
                    .userId(existingUser.getUserId())
                    .username(existingUser.getUsername())
                    .password(updatedUser.getPassword()) // 업데이트할 비밀번호만 변경
                    .build();

            // 새로 생성한 객체를 저장하고 반환
            return userRepository.save(updated);
        }).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
    }


    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
