package cn.huaqucha.JWTAuth;

import cn.huaqucha.entites.User;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Slf4j
@Component
public class Auth {

    public String getKeySource() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("./config/key"));
            String s = br.readLine();
            br.close();
            return s;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String genrateToken(User user) {
        String keySource = getKeySource();
        UUID uuid = UUID.randomUUID();
        JwtBuilder builder = Jwts.builder();
        byte[] keyByte = Base64.getDecoder().decode(keySource.getBytes());
        SecretKey key = Keys.hmacShaKeyFor(keyByte);

        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 3600);

        builder.setClaims(claims);//载荷
        builder.setExpiration(calendar.getTime());//过期时间
        builder.signWith(SignatureAlgorithm.HS256, key);//密钥，记得换成可配置的密钥

        return builder.compact();
    }

    public User decodeToken(String token) {
        User user = new User();
        String keySource = getKeySource();
        SecretKey key = Keys.hmacShaKeyFor(keySource.getBytes());
        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();
            String userInformation = claims.get("user").toString();
            user = JSON.parseObject(userInformation, User.class);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }
}
