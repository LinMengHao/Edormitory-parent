package com.lmh.dormitoryService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.dormitoryService.entity.Build;
import com.lmh.dormitoryService.entity.Dormitory;
import com.lmh.dormitoryService.service.DormitoryService;
import com.lmh.dormitoryService.vo.DormitoryQuery;
import com.lmh.utils.R;
import com.sun.xml.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lmh
 * @since 2020-12-01
 */
@Api(tags = "宿舍管理")
@CrossOrigin
@RestController
@RequestMapping("/dormitoryService/dormitory")
public class DormitoryController {

    @Autowired
    public DormitoryService dormitoryService;

    @ApiOperation(value = "条件分页查询宿舍")
    @PostMapping("findAll/{current}/{size}")
    public R findAll(@PathVariable("current")Long current, @PathVariable("size")Long size,
                     @RequestBody(required = false)DormitoryQuery dormitoryQuery){
        Page<Dormitory> page=new Page<>(current,size);
        QueryWrapper<Dormitory> wrapper=new QueryWrapper<>();
        //条件检验
        if(!StringUtils.isEmpty(dormitoryQuery.getBuildId())){
            wrapper.like("buildId",dormitoryQuery.getBuildId());
        }
        if(!StringUtils.isEmpty(dormitoryQuery.getRoomNum())){
            wrapper.like("room_num",dormitoryQuery.getRoomNum());
        }
        if(!StringUtils.isEmpty(dormitoryQuery.getStatus())){
            wrapper.like("status",dormitoryQuery.getStatus());
        }
        if(!StringUtils.isEmpty(dormitoryQuery.getStorey())){
            wrapper.like("storey",dormitoryQuery.getStorey());
        }
        if(!StringUtils.isEmpty(dormitoryQuery.getMaxNum())){
            wrapper.like("max_num",dormitoryQuery.getMaxNum());
        }
        if(!StringUtils.isEmpty(dormitoryQuery.getNowNum())){
            wrapper.like("now_num",dormitoryQuery.getNowNum());
        }

        wrapper.orderByDesc("create_time");

        dormitoryService.page(page,wrapper);
        long total = page.getTotal();
        List<Dormitory> records = page.getRecords();

        return R.ok().data("total",total).data("records",records);
    }

    TODO 在学生模块中调用按宿舍Id查询学生方法;
    @ApiOperation(value = "根据id查询宿舍成员")
    @GetMapping("getStudentById/{id}")
    public R getStudentById(@PathVariable("id")Integer id){
        return R.ok();
    }

    @ApiOperation(value = "根据id查询宿舍信息")
    @GetMapping("getDormitoryById/{id}")
    public R getDormitoryById(@PathVariable("id")Integer id){
        Dormitory dormitory = dormitoryService.getById(id);
        return R.ok().data("dormitory",dormitory);
    }

    TODO 前端页面保存楼房ID时要查询楼房然后显示提供给宿舍选择楼id保存;
    @ApiOperation(value = "添加宿舍")
    @PostMapping("addDormitory")
    public R addDormitory(){
        return R.ok();
    }

    TODO 用于学生和宿舍绑定和解绑;

    @ApiOperation(value = "根据id减少现有入住人数")
    @PostMapping("reduceNowNum/{id}")
    public R reduceNowNum(@PathVariable("id")Integer id){
        return R.ok();
    }
    @ApiOperation(value = "根据id增加现有入住人数")
    @PostMapping("addNowNum/{id}")
    public R addNowNum(@PathVariable("id")Integer id){
        return R.ok();
    }

}

