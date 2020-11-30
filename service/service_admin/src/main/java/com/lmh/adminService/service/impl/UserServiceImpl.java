package com.lmh.adminService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lmh.adminService.entity.User;
import com.lmh.adminService.mapper.UserMapper;
import com.lmh.adminService.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmh.base.handler.LmhException;
import com.lmh.utils.JwtUtils;
import com.lmh.utils.MD5;
import com.lmh.utils.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表 服务实现类
 *
 * @author lmh
 * @since 2020-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  // 登录,并返回token
  @Override
  public String login(String username, String password) {
    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      throw new LmhException(
          ResultCode.PARAM_IS_BLANK.getCode(), ResultCode.PARAM_IS_BLANK.getMessage());
    }
    // 根据username查询用户
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    User user = baseMapper.selectOne(wrapper);
    if (user == null) {
      throw new LmhException(
          ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
          ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
    }
    // 在判断密码正不正确，加密后的密码
    if (!MD5.encrypt(password).equals(user.getPassword())) {
      throw new LmhException(
          ResultCode.USER_CREDENTIALS_ERROR.getCode(),
          ResultCode.USER_CREDENTIALS_ERROR.getMessage());
    }
    // 在判断是否激活的
    if (user.getStatus() == 0) {
      throw new LmhException(
          ResultCode.USER_ACCOUNT_LOCKED.getCode(), ResultCode.USER_ACCOUNT_LOCKED.getMessage());
    }
    // 登录成功，返回一个保存用户信息的token
    String token = JwtUtils.getJwtToken(user.getId(), user.getNickname());
    return token;
  }

  // 添加用户时密码加密
  @Override
  public void addUser(User user) {
    // 校验
    if (StringUtils.isEmpty(user.getStatus())
        || StringUtils.isEmpty(user.getSex())
        || StringUtils.isEmpty(user.getAvatar())
        || StringUtils.isEmpty(user.getBirth())
        || StringUtils.isEmpty(user.getEmail())
        || StringUtils.isEmpty(user.getNickname())
        || StringUtils.isEmpty(user.getPassword())
        || StringUtils.isEmpty(user.getUsername())) {
      throw new LmhException(
          ResultCode.PARAM_NOT_COMPLETE.getCode(), ResultCode.PARAM_NOT_COMPLETE.getMessage());
    }
    // 根据username查询，如果有结果则用户名被注册，用户名需要唯一
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", user.getUsername());
    User user1 = baseMapper.selectOne(wrapper);
    if (user1 != null) {
      throw new LmhException(
          ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
          ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
    } else {
      // 保存
      String password = user.getPassword();
      user.setPassword(MD5.encrypt(password));
      baseMapper.insert(user);
    }
  }
}
