package com.lmh.adminService.controller;

import com.lmh.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "临时登录功能")
@RestController
@CrossOrigin
@RequestMapping("/adminService/user")
public class LoginController {
  // login
  @PostMapping("login")
  public R login() {
    return R.ok().data("token", "admin");
  }
  // info
  @GetMapping("info")
  public R info() {
    return R.ok()
        .data("roles", "admin")
        .data("name", "张三")
        .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
  }
}
