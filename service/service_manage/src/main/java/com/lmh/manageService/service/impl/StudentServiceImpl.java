package com.lmh.manageService.service.impl;

import com.lmh.manageService.entity.Student;
import com.lmh.manageService.mapper.StudentMapper;
import com.lmh.manageService.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lmh
 * @since 2020-11-27
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
