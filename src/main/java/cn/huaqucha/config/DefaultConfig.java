package cn.huaqucha.config;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Properties;

public class DefaultConfig {

    private static final Logger log = LoggerFactory.getLogger(DefaultConfig.class);

    public DefaultConfig() {

    }

    public void getConfig() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("./config/application.properties"));
    }

    public void create() {
        Boolean b = new File("./config").mkdir();
        try(FileWriter fw = new FileWriter("./config/application.properties");) {
            fw.write(getStringStringMap());
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private static String getStringStringMap() {
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        try(FileWriter fw = new FileWriter("./config/key");){
            fw.write(secretKeyString);
            fw.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "#This is a default config file\n" +
                "server.port=3000\n" +
                "server.ip=127.0.0.1\n" +
                "\n" +
                "#MySQL\n" +
                "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
                "spring.datasource.username={USERNAME}\n" +
                "spring.datasource.password={PASSWORD}\n" +
                "spring.datasource.url=jdbc:mysql://{ADDRESS}:{PORT}/{DATABASE}?useSSL=false\n" +
                "\n" +
                "#Redis\n" +
                "spring.data.redis.host={ADDRESS}\n" +
                "spring.data.redis.port={PORT}\n" +
                "spring.data.redis.password={PASSWORD}\n" +
                "spring.data.redis.database=0\n" +
                "#spring.data.redis.cluster.nodes=127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003\n" +
                "\n" +
                "#Redis lettuce\n" +
                "spring.data.redis.lettuce.pool.max-active=100\n" +
                "spring.data.redis.lettuce.pool.max-idle=50\n" +
                "spring.data.redis.lettuce.pool.min-idle=10\n" +
                "\n" +
                "#Mybatis\n" +
                "mybatis.mapper-locations=classpath:mappers/*.xml\n" +
                "mybatis.xml-mapper-locations=classpath:mapper/db/*.xml\n" +
                "mybatis.type-aliases-package=cn.huaqucha.mapper\n"
                ;
    }

    public void controller(){

    }

    public static void launcher(){
        DefaultConfig conf = new DefaultConfig();
        try {
            conf.getConfig();
        } catch (IOException e) {
            log.info("Create default config...");
            conf.create();
            log.info("Create default config complete.");
            log.error("You need to edit the config.properties file first. And if you want to run this program again.");
            log.error("You can get a help at https://github.com/huaqucha/");
            System.exit(0);
        }


    }



}
