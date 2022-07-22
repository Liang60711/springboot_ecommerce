package com.ricoliang.springboot_ecommerce.service.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.ricoliang.springboot_ecommerce.dao.UserDao;
import com.ricoliang.springboot_ecommerce.dto.request.UserLoginRequest;
import com.ricoliang.springboot_ecommerce.dto.request.UserRegisterRequest;
import com.ricoliang.springboot_ecommerce.model.User;
import com.ricoliang.springboot_ecommerce.service.UserService;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 檢查註冊 email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("Email: {} is already registered !", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 建立帳號
        String salt = getSalt();
        String hashedPassword = sha256(salt, userRegisterRequest.getPassword());

        userRegisterRequest.setPassword(hashedPassword);
        userRegisterRequest.setSalt(salt);

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        // 檢查 email 是否存在
        if (user == null) {
            log.warn("Email {} are not registered yet !", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 檢查帳密
        String hashedLoginPassword = sha256(user.getSalt(), userLoginRequest.getPassword());
        if (user.getPassword().equals(hashedLoginPassword)){
            return user;
        } else {
            log.warn("Password does not match !");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    private String getSalt() {
        char[][] pairs = {{'a','z'},{'A','Z'},{'0','9'}};
        int length = 8;
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange(pairs).build();
        String salt = generator.generate(length);

        return salt;
    }

    private String sha256(String salt,String password){
        return Hashing.sha256().newHasher().putString(salt + password, Charsets.UTF_8).hash().toString();
    }
}
