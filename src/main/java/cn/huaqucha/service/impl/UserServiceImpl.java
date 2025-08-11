package cn.huaqucha.service.impl;

import cn.huaqucha.JWTAuth.Auth;
import cn.huaqucha.entites.User;
import cn.huaqucha.mapper.UserMapper;
import cn.huaqucha.service.UserService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.Base64;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(String email, String phone, String password) {
        String id = getUser(email, phone);
        String uploadPassword = hashPasswordEncoder(password,id);
        return userMapper.getUser(email, phone, uploadPassword);
    }

    @Override
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
    @Override
    public void userRegister(@Nonnull String email, @Nonnull String phone, @Nonnull String password) {
        String name = UUID.randomUUID().toString();
        String hashPassword = hashPasswordEncoder(password, name);
        userMapper.insertUser(name,name,hashPassword,email,name,phone);
    }
    @Override
    public User userAuth(@Nonnull String token) {
        Auth auth = new Auth();
        return auth.decodeToken(token);
    }
    @Override
    public void userUpdatePassword(@Nonnull String id,@Nonnull String pwd){
        String hashPassword = hashPasswordEncoder(pwd,id);
        userMapper.updatePassword(id, hashPassword);
    }

    @Override
    public void updateProfile(User user) {
        userMapper.updateProfile(user.getNickname(),user.getName(),user.getId());
    }

    @Override
    public void updateEmail(String id, String email) {
        userMapper.updateEmail(id, email);
    }

    @Override
    public void updatePhone(String id, String phone) {
        userMapper.updatePhone(id, phone);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }

    private String hashPasswordEncoder(@Nonnull String password, @Nonnull String id) {
        String salt = id.replace("-", "");
        String hexPassword = Base64.getEncoder().encodeToString((password+salt).getBytes());
        return DigestUtils.md5DigestAsHex(hexPassword.getBytes());
    }

    private String getUser(String email, String phone){
        return userMapper.getUserIsNull(email,phone);
    }




}
