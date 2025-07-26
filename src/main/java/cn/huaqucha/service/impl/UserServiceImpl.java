package cn.huaqucha.service.impl;

import cn.huaqucha.JWTAuth.Auth;
import cn.huaqucha.entites.User;
import cn.huaqucha.mapper.UserMapper;
import cn.huaqucha.service.UserService;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User user;


    public User getUser(String email, String phone, String password) {
        String id = getUser(email, phone);
        String uploadPassword = hashPasswordEncoder(password,id);
        return userMapper.getUser(email, phone, uploadPassword);
    }

    private String getUser(String email, String phone){
        return userMapper.getUserIsNull(email,phone);
    }

    public String makeToken(@Nonnull User User) {
        User user = new User();
        user.setId(User.getId());
        user.setName(User.getName());
        user.setEmail(User.getEmail());
        user.setPhone(User.getPhone());
        user.setNickname(User.getNickname());
        user.setUserGroup(User.getUserGroup());

        return new Auth().genrateToken(user);
    }

    public void userRegister(@Nonnull String email, @Nonnull String phone, @Nonnull String password) {
        String name = UUID.randomUUID().toString();
        String hashPassword = hashPasswordEncoder(password, name);
        userMapper.insertUser(name,name,hashPassword,email,name,phone);
    }

    public User userAuth(@Nonnull String token) {
        Auth auth = new Auth();
        return auth.decodeToken(token);
    }

    public void userUpdatePassword(@Nonnull String id,@Nonnull String pwd){
        String hashPassword = hashPasswordEncoder(pwd,id);
        userMapper.updatePassword(id, hashPassword);
    }

    private String hashPasswordEncoder(@Nonnull String password, @Nonnull String id) {
        String salt = id.replace("-", "");
        String hexPassword = Base64.getEncoder().encodeToString((password+salt).getBytes());
        return DigestUtils.md5DigestAsHex(hexPassword.getBytes());
    }




}
