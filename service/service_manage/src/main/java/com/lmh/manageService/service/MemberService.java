package com.lmh.manageService.service;

import com.lmh.manageService.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lmh
 * @since 2020-12-01
 */
public interface MemberService extends IService<Member> {

    List<Member> getByBuildId(Integer buildId);

}
