package cn.huaqucha.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
public class User {

    @Id
    private String id;

    private String nickname;
    private String userGroup;
    private String password;
    private String email;
    private String name;
    private String phone;
}
