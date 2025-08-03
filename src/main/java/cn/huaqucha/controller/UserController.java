package cn.huaqucha.controller;

import cn.huaqucha.dto.UserDTO;
import cn.huaqucha.entites.User;
import cn.huaqucha.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(HttpSession session,
                                            @RequestBody UserDTO userDTO) {
        User user = userService.getUser(userDTO.getEmail(), userDTO.getPhone(), userDTO.getPassword());
        String token = userService.makeToken(user);
        session.setAttribute("token", token);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserDTO userDTO) {
        userService.userRegister(userDTO.getEmail(), userDTO.getPhone(), userDTO.getPassword());
        return ResponseEntity.ok("register success");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.removeAttribute("token");
        return ResponseEntity.ok("logout success");
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO,HttpSession session){
        String id = userDTO.getId();
        String password = userDTO.getPassword();
        userService.userUpdatePassword(id, password);
        session.removeAttribute("token");
        return ResponseEntity.status(200).body("update success,please login");
    }
}
