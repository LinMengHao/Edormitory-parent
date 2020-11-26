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
@CrossOrigin
@Api(tags = "用户表")
@RestController
@RequestMapping("/adminService/user")
public class UserController {
  @Autowired private UserService userService;

  @ApiOperation(value = "条件分页查询用户表")
  @PostMapping("findAll/{current}/{size}")
  public R findAll(
      @PathVariable long current, @PathVariable long size, @RequestBody(required = false)UserQuery userQuery) {
    // 条件查询,条件判断再加入
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    String nickname = userQuery.getNickname();
    String phoneNumber = userQuery.getPhoneNumber();
    String username = userQuery.getUsername();
    Integer sex = userQuery.getSex();
    Integer status = userQuery.getStatus();
    if (!StringUtils.isEmpty(username)) {
      wrapper.like("username", username);
    }
    if (!StringUtils.isEmpty(phoneNumber)) {
      wrapper.like("phone_number", phoneNumber);
    }
    if (!StringUtils.isEmpty(nickname)) {
      wrapper.like("nickname", nickname);
    }
    if (sex!=null) {
      wrapper.eq("sex", sex);
    }
    if (status!=null) {
      wrapper.eq("status", status);
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
  @DeleteMapping("delete/{id}")
  public R delete(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable Long id) {
    boolean flag = userService.removeById(id);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }
  }
}
