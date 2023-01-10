package com.evanpatchouli.demo.controller;

import com.evanpatchouli.demo.controller.service.UserService;
import com.evanpatchouli.demo.model.entity.InfoDto;
import com.evanpatchouli.demo.model.entity.User;
import com.evanpatchouli.demo.util.DtoUtil;
import com.evanpatchouli.demo.util.Result;
import com.evanpatchouli.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {   // Restfule 接口，用json传递
    @Autowired
    private UserService userService;

    @PostMapping("/{model}/{action}")   // http://localhost:8080/api/
    public Map userCount(@PathVariable String model, @PathVariable String action, @RequestParam(required = false) String name, @RequestParam(required = false) String pwd, @RequestParam(required = false, defaultValue = "default") String pwd2, @RequestParam(required = false, defaultValue = "0") int id){
        if(model.equals("user")){
            User user = new User();
            switch (action){
                case "register" :
                    if((!StringUtil.isNotBlank(name))||(!StringUtil.isNotBlank(pwd))){
                        return Result.ResetContent();
                    }
                    user = userService.select(name, pwd);
                    if(user != null){
                        return Result.ResetContent(1,"账号已存在");  //业务/错误编号1，注册账号已存在
                    }
                    user =  new User(name, pwd);
                    userService.register(user);
                    user = userService.select(name, pwd);
                    InfoDto infoDto = new InfoDto(user.getId(),"默认昵称","00000000000");
                    userService.insertInfo(infoDto);
                    return Result.ok(user, "注册成功");
                case "login" :
                    if((!StringUtil.isNotBlank(name))||(!StringUtil.isNotBlank(pwd))){
                        return Result.ResetContent();
                    }
                    user = userService.select(name, pwd);
                    if(user != null){
                        return Result.ok(user,"登录成功");
                    }
                    return Result.Unauthorized(2,"密码错误");   //业务/错误编号2，登录时密码错误
                case "query":
                    user = userService.selectById(id);
                    if(user != null){
                        return Result.ok(user, "success");
                    }
                    break;
                case "updatePwd" :
                    user = userService.select(name, pwd);
                    if(user != null){
                        userService.updatePwd(user.getId(),pwd2);
                        return Result.ok("修改成功");
                    }
                    return Result.Forbidden(3,"密码错误");  //业务/错误编号3，修改密码前校验原密码不通过，不予修改
                case "info" :
                    Map userWithInfo = userService.getUserWithInfo(id);
                    if(userWithInfo != null){
                        return Result.ok(userWithInfo, "携带Info的user");
                    }
                    break;
                default:
                    break;
            }
        }
        return Result.NotFound();
    }

    @PostMapping("/userInfo/{action}")// http://localhost:8080/api/userInfo/
    public Map userInfo(@PathVariable String action, @RequestParam(required = false, defaultValue = "0") int id, @RequestBody(required = false) InfoDto infoDto){
        switch (action) {
            case "insert":{
                if(infoDto==null){
                    return Result.ResetContent();
                }
                if(userService.selectById(infoDto.getId())==null){
                     break;
                }
                if(userService.selectInfo(infoDto.getId())!=null){
                    return Result.ResetContent(4,"已存在");   //业务/错误编号4，要插入的个人信息已经存在
                }
                userService.insertInfo(infoDto);
                Map info = userService.selectInfo(infoDto.getId());
                if(info != null){
                     return Result.ok(info, "success");
                }
                break;
            }
            case "query":{
                Map info = userService.selectInfo(id);
                if(info != null){
                     return Result.ok(info, "success");
                }
                break;
            }
            case "alter":{
                Set ign = new HashSet();
                ign.add("id");
                if(infoDto==null || DtoUtil.hasNoBlank(infoDto,ign)){
                     return Result.ResetContent();
                }
                if(userService.selectById(infoDto.getId())==null){
                     break;
                }
                Map info = userService.updateInfo(infoDto);
                if(infoDto !=null){
                     return Result.ok(info, "success");
                }
                break;
             }
        }
        return Result.NotFound();
    }
}
