package com.lmh.manageService.service.impl;

import com.lmh.base.handler.LmhException;
import com.lmh.manageService.client.DormitoryClient;
import com.lmh.manageService.entity.Student;
import com.lmh.manageService.mapper.StudentMapper;
import com.lmh.manageService.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 服务实现类
 *
 * @author lmh
 * @since 2020-11-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {
  @Autowired private DormitoryClient dormitoryClient;

  @Override
  public boolean addStudent(Student student) {
    dormitoryClient.addNowNum(student.getDormitoryId());
    int insert = baseMapper.insert(student);
    if (insert > 0) {
      return true;
    } else {
      return false;
    }
  }

    @Override
    public boolean deleteStudent(Integer id) {
        Student student = baseMapper.selectById(id);
        dormitoryClient.reduceNowNum(student.getDormitoryId());
        int i = baseMapper.deleteById(id);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
