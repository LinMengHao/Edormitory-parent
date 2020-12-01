package com.lmh.manageService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lmh.manageService.entity.Member;
import com.lmh.manageService.mapper.MemberMapper;
import com.lmh.manageService.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lmh
 * @since 2020-12-01
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public List<Member> getByBuildId(Integer buildId) {
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.eq("build_id",buildId);
        List<Member> members = baseMapper.selectList(wrapper);
        return members;
    }
}
