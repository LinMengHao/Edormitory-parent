package com.lmh.manageService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.manageService.entity.Student;
import com.lmh.manageService.service.StudentService;
import com.lmh.manageService.vo.StudentQuery;
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
 * @since 2020-11-27
 */
@Api(tags = "学生管理")
@CrossOrigin
@RestController
@RequestMapping("/manageService/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //TODO 等写到宿舍和楼字结束后，将宿舍id的条件查询添加，使用SpringCloud调用其他服务方法
    @ApiOperation(value = "条件分页查询学生列表")
    @PostMapping("findAll/{current}/{size}")
    public R findAll(@PathVariable Long current, @PathVariable Long size,
                     @RequestBody(required = false) StudentQuery studentQuery){

        QueryWrapper<Student> wrapper=new QueryWrapper<>();
        //条件判断
        if(!StringUtils.isEmpty(studentQuery.getStudentId())){
            wrapper.like("studentId",studentQuery.getStudentId());
        }
        if(!StringUtils.isEmpty(studentQuery.getName())){
            wrapper.like("name",studentQuery.getName());
        }
        if(!StringUtils.isEmpty(studentQuery.getSex())){
            wrapper.eq("sex",studentQuery.getSex());
        }
        if(!StringUtils.isEmpty(studentQuery.getStatus())){
            wrapper.eq("status",studentQuery.getStatus());
        }
        if(!StringUtils.isEmpty(studentQuery.getDormitoryId())){
            wrapper.like("dormitoryId",studentQuery.getDormitoryId());
        }
        wrapper.orderByDesc("createTime");

        Page<Student> page=new Page<>(current,size);
        studentService.page(page,wrapper);
        long total = page.getTotal();
        List<Student> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    //TODO 添加时要涉及到宿舍绑定，判断宿舍人数，还要双向绑定
    @ApiOperation(value = "添加学生信息")
    @PostMapping("addStudent")
    public R addStudent(@RequestBody Student student){
        return R.ok();
    }


    //TODO 获取学生详细信息时，需要对其他模块功能调用
    @ApiOperation(value = "根据id获取学生详细信息")
    @GetMapping("findById/{id}")
    public R findById(@PathVariable long id){
        return R.ok();
    }


    //TODO 修改学生信息涉及宿舍时，需要调用其他服务
    @ApiOperation(value = "修改学生信息")
    @PostMapping("updateStudent")
    public R updateStudent(@RequestBody Student student){
        return R.ok();
    }

    //TODO 删除学生时，要把绑定的宿舍做相应的修改
    @ApiOperation(value = "删除学生信息")
    @DeleteMapping("deleteStudent")
    public R deleteStudent(){
        return R.ok();
    }
}

