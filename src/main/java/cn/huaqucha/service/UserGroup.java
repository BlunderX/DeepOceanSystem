package cn.huaqucha.service;

import cn.huaqucha.entites.User;
import cn.huaqucha.entites.UserGroups;

import java.util.List;
import java.util.Map;

public interface UserGroup {
    List<Map<String,Object>> getUserGroups();
    UserGroups getUserGroupsById(int id);
    void setUserGroups(UserGroups userGroups);
    void deleteUserGroups(int gid);
    void updateUserGroups(UserGroups userGroups);

    void insertUser(int gid,String id);
    void removeUser(String id);
    User getUser(String id);
}
