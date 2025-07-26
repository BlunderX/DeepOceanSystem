package cn.huaqucha.controller;

import cn.huaqucha.entites.User;
import cn.huaqucha.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
                            HttpServletRequest request,
                            @RequestParam(defaultValue = "") String email,
                            @RequestParam(defaultValue = "") String phone,
                            @RequestParam(defaultValue = "") String password) {
        User user = userService.getUser(email, phone, password);
        String token = userService.makeToken(user);
        session.setAttribute("token", token);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(HttpServletRequest req){
        userService.userRegister(req.getParameter("email"), req.getParameter("phone"), req.getParameter("password"));
        return ResponseEntity.ok("register success");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.removeAttribute("token");
        return ResponseEntity.ok("logout success");
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> update(HttpServletRequest req,HttpSession session){
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        userService.userUpdatePassword(id, password);
        session.removeAttribute("token");
        return ResponseEntity.status(200).body("update success,please login");
    }
}
