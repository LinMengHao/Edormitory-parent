package com.lmh.dormitoryService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.base.handler.LmhException;
import com.lmh.dormitoryService.client.ManageClient;
import com.lmh.dormitoryService.entity.Build;
import com.lmh.dormitoryService.entity.Dormitory;
import com.lmh.dormitoryService.service.DormitoryService;
import com.lmh.dormitoryService.vo.DormitoryQuery;
import com.lmh.utils.R;
import com.lmh.utils.ResultCode;
import com.sun.xml.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制器
 *
 * @author lmh
 * @since 2020-12-01
 */
@Api(tags = "宿舍管理")
@CrossOrigin
@RestController
@RequestMapping("/dormitoryService/dormitory")
public class DormitoryController {

  @Autowired public DormitoryService dormitoryService;
  @Autowired public ManageClient manageClient;

  @ApiOperation(value = "条件分页查询宿舍")
  @PostMapping("findAll/{current}/{size}")
  public R findAll(
      @PathVariable("current") Long current,
      @PathVariable("size") Long size,
      @RequestBody(required = false) DormitoryQuery dormitoryQuery) {
    Page<Dormitory> page = new Page<>(current, size);
    QueryWrapper<Dormitory> wrapper = new QueryWrapper<>();
    // 条件检验
    if (!StringUtils.isEmpty(dormitoryQuery.getBuildId())) {
      wrapper.like("build_id", dormitoryQuery.getBuildId());
    }
    if (!StringUtils.isEmpty(dormitoryQuery.getRoomNum())) {
      wrapper.like("room_num", dormitoryQuery.getRoomNum());
    }
    if (!StringUtils.isEmpty(dormitoryQuery.getStatus())) {
      wrapper.like("status", dormitoryQuery.getStatus());
    }
    if (!StringUtils.isEmpty(dormitoryQuery.getStorey())) {
      wrapper.like("storey", dormitoryQuery.getStorey());
    }
    // 大于等于
    if (!StringUtils.isEmpty(dormitoryQuery.getMaxNum())) {
      wrapper.ge("max_num", dormitoryQuery.getMaxNum());
    }
    // 小于等于
    if (!StringUtils.isEmpty(dormitoryQuery.getNowNum())) {
      wrapper.le("now_num", dormitoryQuery.getNowNum());
    }

    wrapper.orderByDesc("create_time");

    dormitoryService.page(page, wrapper);
    long total = page.getTotal();
    List<Dormitory> records = page.getRecords();

    return R.ok().data("total", total).data("records", records);
  }

  TODO 在学生模块中调用按宿舍Id查询学生方法;

  @ApiOperation(value = "根据宿舍id查询宿舍成员")
  @GetMapping("getStudentById/{id}")
  public R getStudentById(@PathVariable("id") Integer id) {
    R r = manageClient.getStudentsByDormitoryId(id);
    if (r.getCode() == 999) {
      throw new LmhException(ResultCode.COMMON_FAIL.getCode(), "获取宿舍成员信息失败，服务器熔断");
    }
    return r;
  }

  @ApiOperation(value = "根据id查询宿舍信息")
  @GetMapping("getDormitoryById/{id}")
  public R getDormitoryById(@PathVariable("id") Integer id) {
    Dormitory dormitory = dormitoryService.getById(id);
    return R.ok().data("dormitory", dormitory);
  }

  TODO 前端页面保存楼房ID时要查询楼房然后显示提供给宿舍选择楼id保存;

  @ApiOperation(value = "添加宿舍")
  @PostMapping("addDormitory")
  public R addDormitory(@RequestBody Dormitory dormitory) {
    boolean save = dormitoryService.save(dormitory);
    if (save) {
      return R.ok();
    } else {
      return R.error();
    }
  }

  TODO 用于学生和宿舍绑定和解绑;

  @ApiOperation(value = "根据id减少现有入住人数")
  @PostMapping("reduceNowNum/{id}")
  public R reduceNowNum(@PathVariable("id") Integer id) {
    Boolean flag = dormitoryService.reduceNowNum(id);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }
  }

  @ApiOperation(value = "根据id增加现有入住人数")
  @PostMapping("addNowNum/{id}")
  public R addNowNum(@PathVariable("id") Integer id) {

    Boolean flag = dormitoryService.addNowNum(id);
    if (flag) {
      return R.ok();
    } else {
      return R.error();
    }
  }

  // 删除宿舍前提是无人员居住时删除条件为now_num值为0;
  @ApiOperation(value = "删除宿舍")
  @DeleteMapping("deleteDormitory/{id}")
  public R deleteDormitory(@PathVariable("id") Integer id) {
    QueryWrapper<Dormitory> wrapper = new QueryWrapper<>();
    wrapper.eq("now_num", 0);
    wrapper.eq("id", id);
    boolean flag = dormitoryService.remove(wrapper);
    if (flag) {
      return R.ok();
    } else {
      return R.error()
          .code(ResultCode.DORMITORY_NOT_DELETE.getCode())
          .message(ResultCode.DORMITORY_NOT_DELETE.getMessage());
    }
  }

  @ApiOperation(value = "修改宿舍信息")
  @PostMapping("updateDormitory")
  public R updateDormitory(@RequestBody Dormitory dormitory) {
      boolean flag = dormitoryService.updateById(dormitory);
      if (flag) {
        return R.ok();
      } else {
        return R.error();
      }
  }
}
