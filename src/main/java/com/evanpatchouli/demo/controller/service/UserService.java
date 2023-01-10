package com.evanpatchouli.demo.controller.service;

import com.evanpatchouli.demo.model.entity.InfoDto;
import com.evanpatchouli.demo.model.entity.User;
import com.evanpatchouli.demo.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    //注册账号
    public void register(User user){ userMapper.insert(user); };
    //登录账号时，查询这样的一对用户名、密码的账号
    public User select(String name, String pwd){
        return userMapper.select(name, pwd);
    }
    //修改指定id的账号的密码
    public void  updatePwd(int id, String pwd) { userMapper.updatePwd(id, pwd); }
    // 获取指定id的账号信息
    public User selectById(int id){ return userMapper.selectById(id); }
    // 插入指定指定账号id的info
    public void insertInfo(InfoDto infoDto) { userMapper.insertInfo(infoDto); }
    // 获取指定账号id的info信息
    public Map selectInfo(int id) { return userMapper.selectInfo(id); }
    // 更新指定账号id的info信息
    public Map updateInfo(InfoDto infoDto) {
        userMapper.updateInfo(infoDto);
        return userMapper.selectInfo(infoDto.getId());
    }
    // 获取指定id的账号信息，同时携带上对应的info信息
    public Map getUserWithInfo(int id){
        return userMapper.selectUserWithInfo(id);
    }
}
