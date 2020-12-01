package com.lmh.adminService.controller;

import com.lmh.adminService.entity.User;
import com.lmh.adminService.service.UserService;
import com.lmh.utils.JwtUtils;
import com.lmh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// TODO 最后改进登录功能，实现token携带用户信息
@Api(tags = "临时登录功能")
@RestController
@CrossOrigin
@RequestMapping("/adminService/user")
public class LoginController {
  @Autowired private UserService userService;
//    // login
//    @PostMapping("login")
//    public R login() {
//      return R.ok().data("token", "admin");
//    }
//    // info
//    @GetMapping("info")
//    public R info() {
//      return R.ok()
//          .data("roles", "admin")
//          .data("name", "张三")
//          .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//    }

  // login正式版
  @ApiOperation(value = "登录并携带token")
  @PostMapping("login")
  public R login(@RequestBody User user) {
    String token = userService.login(user.getUsername(), user.getPassword());
    return R.ok().data("token",token);
  }

  @ApiOperation(value = "获取token里面的信息")
  @GetMapping("info")
  public R info(HttpServletRequest request) {
    Integer id = JwtUtils.getMemberIdByJwtToken(request);

    User user=userService.getById(id);
    if (user != null) {
      return R.ok()
          .data("roles", "admin")
          .data("name", user.getNickname())
          .data("avatar", user.getAvatar());
    }else {
      return R.error();
    }
  }
}
