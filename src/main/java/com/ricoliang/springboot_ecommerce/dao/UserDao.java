package com.ricoliang.springboot_ecommerce.dao;

import com.ricoliang.springboot_ecommerce.dto.request.UserRegisterRequest;
import com.ricoliang.springboot_ecommerce.model.User;

public interface UserDao {

    User getUserById(Integer Userid);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
