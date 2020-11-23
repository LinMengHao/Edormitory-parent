package com.lmh.adminService.service.impl;

import com.lmh.adminService.entity.User;
import com.lmh.adminService.mapper.UserMapper;
import com.lmh.adminService.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lmh
 * @since 2020-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
