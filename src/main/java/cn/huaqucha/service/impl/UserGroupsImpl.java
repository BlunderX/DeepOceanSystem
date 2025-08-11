package cn.huaqucha.service.impl;


import cn.huaqucha.entites.User;
import cn.huaqucha.entites.UserGroups;
import cn.huaqucha.mapper.UserGroupsMapper;
import cn.huaqucha.mapper.UserMapper;
import cn.huaqucha.service.UserGroup;
import io.netty.handler.codec.base64.Base64Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserGroupsImpl implements UserGroup {

    private final UserMapper userMapper;
    private final UserGroupsMapper userGroupsMapper;
    @Autowired
    public UserGroupsImpl(UserMapper userMapper,
                          UserGroupsMapper userGroupsMapper) {
        this.userMapper = userMapper;
        this.userGroupsMapper = userGroupsMapper;
    }


    @Override
    public UserGroups getUserGroupsById(int id) {
        return userGroupsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map<String,Object>> getUserGroups() {
        List<Map<String,Object>> ls = userGroupsMapper.findAll();
        for (Map<String,Object> map : ls) {
            Object data = map.get("data");
            Object aside = map.get("aside");
            if (data instanceof byte[] && aside instanceof byte[]) {
                map.put("data", decodeBase64((byte[]) data));
                map.put("aside", decodeBase64((byte[]) aside));
            }
        }
        return ls;
    }

    @Override
    public void setUserGroups(UserGroups userGroups) {
        userGroupsMapper.insert(userGroups.getName(),userGroups.getDespriction(),userGroups.getData());
    }

    @Override
    public void deleteUserGroups(int gid) {
        userGroupsMapper.deleteByPrimaryKey(gid);
    }

    @Override
    public void updateUserGroups(UserGroups userGroups) {
        userGroupsMapper.updateByPrimaryKey(userGroups.getId(),userGroups.getName(),userGroups.getDespriction(),userGroups.getData(),userGroups.getAside());
    }

    @Override
    public void insertUser(int gid,String id) {
        userMapper.updateUserGroup(gid,id);
    }

    @Override
    public void removeUser(String id) {
        userMapper.updateUserGroup(1,id);
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    private String decodeBase64(byte[] bytes) {
        String s = Base64.getEncoder().encodeToString(bytes);
        byte[] b = Base64.getDecoder().decode(s);
        return new String(b, StandardCharsets.UTF_8);
    }
}
