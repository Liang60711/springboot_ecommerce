package com.ricoliang.springboot_ecommerce.controller;

import com.ricoliang.springboot_ecommerce.dto.request.UserLoginRequest;
import com.ricoliang.springboot_ecommerce.dto.request.UserRegisterRequest;
import com.ricoliang.springboot_ecommerce.model.User;
import com.ricoliang.springboot_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 註冊
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {

        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    /**
     * 登入
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {

        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
