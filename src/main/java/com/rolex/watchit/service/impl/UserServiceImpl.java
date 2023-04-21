package com.rolex.watchit.service.impl;


import com.rolex.watchit.model.User;
import com.rolex.watchit.repository.UserRepository;
import com.rolex.watchit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User updateUserDescription(Long userId, String description) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with " + userId + " doesn't exist!"));
        if (description != null){
            user.setDescription(description);
        }
        return userRepository.save(user);
    }
}
