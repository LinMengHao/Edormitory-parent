package com.lmh.adminService.service;

import com.lmh.adminService.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lmh
 * @since 2020-11-23
 */
public interface UserService extends IService<User> {

    String login(String username, String password);

    void addUser(User user);
}
