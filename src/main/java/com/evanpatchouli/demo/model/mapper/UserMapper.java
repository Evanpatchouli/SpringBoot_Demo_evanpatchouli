package com.evanpatchouli.demo.model.mapper;

import com.evanpatchouli.demo.model.entity.InfoDto;
import com.evanpatchouli.demo.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    @Insert(value = "insert into user(name,pwd) values (#{name},#{pwd})")
    void insert(User user);

    @Select("select * from user where name=#{name} and pwd=#{pwd}")
    User select(@Param("name") String name, @Param("pwd") String pwd);

    @Select("select * from user where id=#{id}")
    User selectById(int id);

    @Update("update user set pwd=#{pwd} where id=#{id}")
    void updatePwd(@Param("id") int id,@Param("pwd") String pwd);

    @Insert("insert into userinfo(id,nick,phone) values (#{id},#{nick},#{phone})")
    void insertInfo(InfoDto infoDto);

    @Update(value = "update userinfo set nick=#{nick},phone=#{phone} where id=#{id}")
    void updateInfo(InfoDto infoDto);
    // Map映射接受结果
    @Select("select * from userinfo where id=#{id}")
    Map selectInfo(int id);

    @Select("select * from user u,userinfo i where u.id=#{id} and u.id=i.id")
    Map selectUserWithInfo(int id);
}
