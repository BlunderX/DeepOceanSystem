package cn.huaqucha.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@Entity
public class UserGroups {

    @Id
    private int id;

    private String name;
    private String despriction;
    private String data;
    private String aside;
}
