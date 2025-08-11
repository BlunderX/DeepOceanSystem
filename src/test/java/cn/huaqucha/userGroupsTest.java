package cn.huaqucha;

import cn.huaqucha.entites.User;
import cn.huaqucha.entites.UserGroups;
import cn.huaqucha.mapper.UserGroupsMapper;
import com.alibaba.fastjson2.JSON;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class UserGroupsTest {

    @Autowired
    UserGroupsMapper userGroupsMapper;
    @Autowired
    UserGroups userGroups;
    @Autowired
    private User user;

    @Test
    void testSelectByPrimaryKey() {
        log.info("testSelectByPrimaryKey");
        UserGroups userGroups = userGroupsMapper.selectByPrimaryKey(1);
        System.out.println(userGroups.toString());
    }
    @Test
    void testFindAll(){
        log.info("testFindAll");
        List<Map<String, Object>> userGroups = userGroupsMapper.findAll();
        log.info(userGroups.toString());
    }


}
