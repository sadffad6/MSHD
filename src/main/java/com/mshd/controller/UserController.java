package com.mshd.controller;
import com.mshd.mapper.UserMapper;
import com.mshd.utils.jwt.JwtUtil;
import com.mshd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mshd.utils.jwt.JwtToken;
import com.mshd.utils.R;
import java.util.List;

import com.mshd.pojo.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;  // 通过依赖注入获取 JwtUtil 实例
    @Autowired
    private UserMapper userMapper;


    @PostMapping("/login")
    public R login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String storedPassword = userService.getPassword(username);
        if (storedPassword != null && storedPassword.equals(password)) {

            String token = jwtUtil.sign(username, password);  // 使用实例方法而非静态方法
            return R.ok("登录成功", token);
        } else {
            return R.failure("用户名或密码错误");
        }
    }

    @PostMapping("/signup")
    public R signup(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("re_password") String re_password) {
        if (username != null && password != null) {
            boolean success = userService.addUser(username, password);
            if (success) {
                return R.ok("注册成功");
            }
        }
        return R.failure("注册失败");
    }

    @DeleteMapping("/delete")
    public R delete(@RequestParam("id") String id) {
        try{
            userMapper.delete(id);
            return R.ok("删除用户成功");
        }catch (Exception e){
            e.printStackTrace();
            return R.failure(e.getMessage());
        }
    }

    @GetMapping("/all")
    public R all() {
        List<User> userList = userService.getAllUsers();
        return R.ok(userList);
    }

    @PostMapping("/change_name")
    public R changeName(@RequestParam("id") String id, @RequestParam("name") String name) {
        int yn = userService.updateUsername(id, name);
        return R.ok(yn);
    }
}