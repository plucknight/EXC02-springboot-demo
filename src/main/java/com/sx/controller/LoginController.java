package com.sx.controller;

import com.sx.pojo.Emp;
import com.sx.pojo.Result;
import com.sx.service.EmpService;
import com.sx.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    //将json格式数据封装到实体类中
    public Result login(@RequestBody Emp emp){
        log.info("员工登入：{}",emp);
        Emp e=empService.login(emp);

        if(e!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return e!=null?Result.success():Result.error("用户名或密码错误");
    }

}
