package cn.huaqucha.controller.admin;

import cn.huaqucha.entites.UserGroups;
import cn.huaqucha.service.UserGroup;
import cn.huaqucha.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/groups")
public class UserGroupController {

    private final UserGroup userGroup;
    private final UserService userService;
    @Autowired
    public UserGroupController(UserGroup userGroup, UserService userService) {
        this.userGroup = userGroup;
        this.userService = userService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Map<String, Object>>> getAll(){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userGroup.getUserGroups());
    }

    @GetMapping("getById")
    public ResponseEntity<UserGroups> getUserGroupById(@RequestParam int id){
        return ResponseEntity.ok().body(userGroup.getUserGroupsById(id));
    }

    @GetMapping("/deleteGroup")
    public ResponseEntity<String> deleteUserGroups(@RequestParam int gid){
        userGroup.deleteUserGroups(gid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/setNewGroup")
    public ResponseEntity<String> setUserGroups(@RequestBody UserGroups userGroups){
        userGroup.setUserGroups(userGroups);
        return ResponseEntity.ok().build();
    }

    //update group
    @PostMapping("/updateGroup")
    public ResponseEntity<String> updateGroup(@RequestBody UserGroups userGroups){
        userGroup.updateUserGroups(userGroups);
        log.info(String.valueOf(userGroup.getUserGroups()));
        return ResponseEntity.ok().build();
    }

    //update user
    @GetMapping("/updateUserGroup")
    public ResponseEntity<String> insertUser(@RequestParam int gid, @RequestParam String id){
        userGroup.insertUser(gid, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/removeUserGroup")
    public ResponseEntity<String> removeUser(@RequestParam String id){
        userGroup.removeUser(id);
        return ResponseEntity.ok().build();
    }






}
