package cn.huaqucha.service;

import cn.huaqucha.entites.User;

import java.util.Map;

public interface UserService {
    User getUser(String email, String phone, String password);
    String makeToken(User userDAO);
    void userRegister(String email, String phone, String password);
    User userAuth(String token);
    void userUpdatePassword(String id,String pwd);

}
