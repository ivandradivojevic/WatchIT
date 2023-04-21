package com.rolex.watchit.controller;


import com.rolex.watchit.auth.AuthenticationService;
import com.rolex.watchit.dtos.UserDescriptionDto;
import com.rolex.watchit.model.User;
import com.rolex.watchit.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PutMapping
    private User updateUserDesc(HttpServletRequest httpServletRequest, @RequestBody UserDescriptionDto userDescriptionDto) throws IOException {
        Long id = authenticationService.getIdOfUser(httpServletRequest);
        return userService.updateUserDescription(id,userDescriptionDto.getDescription());
    }
}
