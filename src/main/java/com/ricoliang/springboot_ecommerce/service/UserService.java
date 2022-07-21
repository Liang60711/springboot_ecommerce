package com.ricoliang.springboot_ecommerce.service;

import com.ricoliang.springboot_ecommerce.dto.UserLoginRequest;
import com.ricoliang.springboot_ecommerce.dto.UserRegisterRequest;
import com.ricoliang.springboot_ecommerce.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

    User getUserById(Integer userId);

}
