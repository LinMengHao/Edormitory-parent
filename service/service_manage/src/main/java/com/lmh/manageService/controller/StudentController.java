package com.lmh.manageService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.base.handler.LmhException;
import com.lmh.manageService.client.DormitoryClient;
import com.lmh.manageService.entity.Student;
import com.lmh.manageService.service.StudentService;
import com.lmh.manageService.vo.StudentQuery;
import com.lmh.utils.R;
import com.lmh.utils.ResultCode;
import com.sun.xml.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private DormitoryClient dormitoryClient;
    //TODO 等写到宿舍和楼字结束后，将宿舍id的条件查询添加，使用SpringCloud调用其他服务方法
    @ApiOperation(value = "条件分页查询学生列表")
    @PostMapping("findAll/{current}/{size}")
    public R findAll(@PathVariable Long current, @PathVariable Long size,
                     @RequestBody(required = false) StudentQuery studentQuery){

        QueryWrapper<Student> wrapper=new QueryWrapper<>();
        //条件判断
        if(!StringUtils.isEmpty(studentQuery.getStudentId())){
            wrapper.like("student_id",studentQuery.getStudentId());
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
        if(!StringUtils.isEmpty(studentQuery.getPhoneNum())){
            wrapper.like("phone_num",studentQuery.getPhoneNum());
        }
        if(!StringUtils.isEmpty(studentQuery.getDormitoryId())){
            wrapper.like("dormitory_id",studentQuery.getDormitoryId());
        }
        wrapper.orderByDesc("create_time");

        Page<Student> page=new Page<>(current,size);
        studentService.page(page,wrapper);
        long total = page.getTotal();
        List<Student> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);
    }


    //学号唯一
    @ApiOperation(value = "添加学生信息")
    @PostMapping("addStudent")
    public R addStudent(@RequestBody Student student){
        QueryWrapper<Student> wrapper=new QueryWrapper<>();
        wrapper.eq("student_id",student.getStudentId());
        Student one = studentService.getOne(wrapper);
        if(one!=null){
            return R.error().code(ResultCode.STUDENT_ID_EXIST.getCode()).message(ResultCode.STUDENT_ID_EXIST.getMessage());
        }
        try{
            boolean flag=studentService.addStudent(student);
            if(flag){
                return R.ok();
            }else {
                return R.error();
            }
        }catch (Exception e){
            throw new LmhException(ResultCode.STUDENT_ID_EXIST.getCode(),ResultCode.STUDENT_ID_EXIST.getMessage());
        }
    }



    @ApiOperation(value = "根据id获取学生详细信息")
    @GetMapping("findById/{id}")
    public R findById(@PathVariable Integer id){

        Student student = studentService.getById(id);
        return R.ok().data("student",student);
    }


    //不可以在学生列表修改宿舍，需要去专门的换宿舍功能模块
    @ApiOperation(value = "修改学生信息")
    @PostMapping("updateStudent")
    public R updateStudent(@RequestBody Student student){
        boolean flag = studentService.updateById(student);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @ApiOperation(value = "删除学生信息")
    @DeleteMapping("deleteStudent/{id}")
    public R deleteStudent(@PathVariable("id")Integer id){
        boolean flag = studentService.deleteStudent(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "更加宿舍id查询学生列表")
    @GetMapping("getStudentsByDormitoryId/{dormitoryId}")
    public R getStudentsByDormitoryId(@PathVariable("dormitoryId")Integer dormitoryId){
        QueryWrapper<Student> wrapper=new QueryWrapper<>();
        wrapper.eq("dormitory_id",dormitoryId);
        List<Student> students = studentService.list(wrapper);
        return R.ok().data("students",students);
    }
}

