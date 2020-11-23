package com.lmh.adminService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.adminService.entity.User;
import com.lmh.adminService.service.UserService;
import com.lmh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lmh
 * @since 2020-11-23
 */
@Api(tags = "用户表")
@RestController
@RequestMapping("/adminService/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页查询用户表")
    @PostMapping("/findAll")
    public R findAll(@RequestParam(name = "current",required = true,defaultValue = "1")Integer current,
                     @RequestParam(name = "size",required =true,defaultValue = "10")Integer size){
        Page<User> page=new Page<>(current,size);
        userService.page(page);
        Long total=page.getTotal();
        List<User> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
}

