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
        // userId에 해당하는 유저가 존재하는지 확인
        return userRepository.findById(userId).map(existingUser -> {
            // 비밀번호 업데이트
            existingUser.setPassword(updatedUser.getPassword());
            // 업데이트된 유저를 저장하고 반환
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
