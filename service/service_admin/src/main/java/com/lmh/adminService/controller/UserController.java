package com.lmh.adminService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.adminService.entity.User;
import com.lmh.adminService.service.UserService;
import com.lmh.adminService.vo.UserQuery;
import com.lmh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表 前端控制器
 *
 * @author lmh
 * @since 2020-11-23
 */
@Api(tags = "用户表")
@RestController
@RequestMapping("/adminService/user")
public class UserController {
  @Autowired private UserService userService;

  @ApiOperation(value = "条件分页查询用户表")
  @PostMapping("findAll/{current}/{size}")
  public R findAll(
      @PathVariable long current, @PathVariable long size, @RequestBody UserQuery userQuery) {
    // 条件查询,条件判断再加入
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    if (!StringUtils.isEmpty(userQuery.getUsername())) {
      wrapper.like("username", userQuery.getUsername());
    }
    if (!StringUtils.isEmpty(userQuery.getPhoneNumber())) {
      wrapper.like("phone_number", userQuery.getPhoneNumber());
    }
    if (!StringUtils.isEmpty(userQuery.getNickname())) {
      wrapper.like("nickname", userQuery.getNickname());
    }
    if (!StringUtils.isEmpty(userQuery.getEmail())) {
      wrapper.like("email", userQuery.getEmail());
    }
    if (!StringUtils.isEmpty(userQuery.getSex())) {
      wrapper.eq("sex", userQuery.getSex());
    }
    if (!StringUtils.isEmpty(userQuery.getStatus())) {
      wrapper.eq("status", userQuery.getStatus());
    }
    // 按创建时间排序
    wrapper.orderByDesc("create_time");

    // 分页
    Page<User> page = new Page<>(current, size);
    userService.page(page, wrapper);
    Long total = page.getTotal();
    List<User> records = page.getRecords();
    return R.ok().data("total", total).data("records", records);
  }

  @ApiOperation(value = "添加用户")
    @PostMapping("addUser")
    public R addUser(@RequestBody User user){
      Boolean flag=userService.save(user);
      if(flag){
          return R.ok();
      }else {
          return R.error();
      }
  }

  @ApiOperation(value = "根据Id查询用户")
    @GetMapping("findById/{id}")
    public R findById(@PathVariable Long id){
      User user = userService.getById(id);
      return R.ok().data("user",user);
  }

  @ApiOperation(value = "修改用户")
    @PostMapping("update")
    public R update(@RequestBody User user){
      boolean flag = userService.updateById(user);
      if(flag){
          return R.ok();
      }else {
          return R.error();
      }
  }

  @ApiOperation(value = "根据id，逻辑删除")
  @PostMapping("delete/{id}")
  public R delete(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable Long id) {
    boolean flag = userService.removeById(id);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }
  }
}
