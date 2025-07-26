package cn.huaqucha.mapper;


import cn.huaqucha.entites.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
