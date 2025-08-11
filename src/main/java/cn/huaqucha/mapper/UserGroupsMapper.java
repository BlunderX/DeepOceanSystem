package cn.huaqucha.mapper;

import cn.huaqucha.entites.UserGroups;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserGroupsMapper {

    @Select("select * from usergroups")
    @MapKey("id")
    List<Map<String,Object>> findAll();

    @Select("select * from usergroups where id = #{id}")
    UserGroups selectByPrimaryKey(int id);

    @Insert("insert into usergroups (name, despriction, data) values (#{name},#{despriction},#{data})")
    void insert(String name, String despriction, String data);

    @Update("update usergroups set name = #{name},despriction = #{despriction},data = #{data},aside = #{aside} where id=#{id}")
    void updateByPrimaryKey(int id,String name, String despriction, String data,String aside);

    @Delete("delete from usergroups where id = #{key}")
    void deleteByPrimaryKey(int key);
}
