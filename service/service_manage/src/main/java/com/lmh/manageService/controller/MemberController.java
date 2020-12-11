package com.lmh.manageService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.manageService.entity.Member;
import com.lmh.manageService.service.MemberService;
import com.lmh.manageService.vo.MemberQuery;
import com.lmh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前端控制器
 *
 * @author lmh
 * @since 2020-12-01
 */
@Api(tags = "宿舍管理员")
@CrossOrigin
@RestController
@RequestMapping("/manageService/member")
public class MemberController {
  @Autowired private MemberService memberService;

  @ApiOperation(value = "根据id查询宿管信息")
  @GetMapping("getByBuildId/{buildId}")
  public R getByBuildId(@PathVariable Integer buildId) {
    List<Member> members = memberService.getByBuildId(buildId);
    return R.ok().data("members", members);
  }

  @ApiOperation(value = "根据id查询宿管信息")
  @GetMapping("getByMemberId/{id}")
  public R getByMemberId(@PathVariable("id") Integer id) {
    Member member = memberService.getById(id);
    return R.ok().data("member", member);
  }

  @ApiOperation(value = "条件分页查询")
  @PostMapping("findAll/{current}/{size}")
  public R findAll(
      @PathVariable("current") Long current,
      @PathVariable("size") Long size,
      @RequestBody(required = false) MemberQuery memberQuery) {
    Page<Member> page = new Page<>(current, size);
    QueryWrapper<Member> wrapper = new QueryWrapper<>();
    // 条件检验
    if (!StringUtils.isEmpty(memberQuery.getBuildId())) {
      wrapper.like("build_id", memberQuery.getBuildId());
    }
    if (!StringUtils.isEmpty(memberQuery.getName())) {
      wrapper.like("name", memberQuery.getName());
    }
    if (!StringUtils.isEmpty(memberQuery.getStatus())) {
      wrapper.eq("status", memberQuery.getStatus());
    }
    if (!StringUtils.isEmpty(memberQuery.getPhoneNum())) {
      wrapper.like("phone_num", memberQuery.getPhoneNum());
    }
    if (!StringUtils.isEmpty(memberQuery.getSex())) {
      wrapper.eq("sex", memberQuery.getSex());
    }
    if (!StringUtils.isEmpty(memberQuery.getAddress())) {
      wrapper.like("address", memberQuery.getAddress());
    }

    wrapper.orderByDesc("create_time");
    memberService.page(page, wrapper);
    List<Member> records = page.getRecords();
    long total = page.getTotal();
    return R.ok().data("total", total).data("records", records);
  }

  @ApiOperation(value = "增加宿管")
  @PostMapping("addMember")
  public R addMember(@RequestBody Member member) {
    boolean save = memberService.save(member);
    if (save) {
      return R.ok();
    } else {
      return R.error();
    }
  }

  @ApiOperation(value = "修改")
  @PostMapping("updateMember")
  public R updateMember(@RequestBody Member member) {
    boolean b = memberService.updateById(member);
    if (b) {
      return R.ok();
    } else {
      return R.error();
    }
  }

  @ApiOperation(value = "删除")
  @DeleteMapping("deleteMember/{id}")
  public R deleteMember(@PathVariable("id") Integer id) {
    boolean b = memberService.removeById(id);
    if (b) {
      return R.ok();
    } else {
      return R.error();
    }
  }

  @ApiOperation(value = "查询全部")
  @GetMapping("getAllMember")
  public R getMember() {
    List<Member> list = memberService.list();
    return R.ok().data("memberList", list);
  }
  // 判断用户评分身份是否为本人操作
  @ApiOperation(value = "根据姓名和电话号码查询宿管")
  @PostMapping("findByPhoneNumAndName")
  public R findByPhoneNumAndName(
      @RequestParam("name") String name, @RequestParam("phoneNum") String phoneNum) {
    QueryWrapper<Member> wrapper = new QueryWrapper<>();
    wrapper.eq("name", name);
    wrapper.eq("phone_num", phoneNum);
    Member one = memberService.getOne(wrapper);
    if (one != null) {
      return R.ok();
    } else {
      return R.error();
    }
  }
}
