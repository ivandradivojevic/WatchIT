package com.rolex.watchit.service;

import com.rolex.watchit.model.User;

public interface UserService {
    User updateUserDescription(Long userId, String description);
}
