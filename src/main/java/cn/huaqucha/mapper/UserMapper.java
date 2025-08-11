package cn.huaqucha.mapper;


import cn.huaqucha.entites.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where (email = #{email} or phone = #{phone}) and password = #{password};")
    User getUser(String email, String phone, String password);

    @Select("select id from user where email = #{email} or phone = #{phone}")
    String getUserIsNull(String email, String phone);

    @Insert("insert into user(id, nickname, password, email, name, phone) " +
            "value (#{id},#{nickname},#{password},#{email},#{name},#{phone})")
    void insertUser(String id, String nickname, String password, String email, String name, String phone);

    @Update("update user set password = #{password} where id = #{id}")
    void updatePassword(String password, String id);

    @Update("update user set nickname = #{nickname},name=#{name} where id = #{id}")
    void updateProfile(String nickname, String name,String id);

    @Update("update user set email = #{email} where id = #{id}")
    void updateEmail(String email,String id);

    @Update("update user set phone = #{phone} where id = #{id}")
    void updatePhone(String phone,String id);

    @Update("update user set userGroup = #{gid} where id = #{id}")
    void updateUserGroup(int gid,String id);

    @Delete("delete from user where id = #{id}")
    void deleteUser(String id);
}
