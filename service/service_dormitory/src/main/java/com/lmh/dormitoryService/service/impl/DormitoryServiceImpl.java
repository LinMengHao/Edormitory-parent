package com.lmh.dormitoryService.service.impl;

import com.lmh.dormitoryService.entity.Dormitory;
import com.lmh.dormitoryService.mapper.DormitoryMapper;
import com.lmh.dormitoryService.service.DormitoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {


    @Override
    public Boolean reduceNowNum(Integer id) {
        return baseMapper.reduceNowNum(id);
    }

    @Override
    public Boolean addNowNum(Integer id) {
        return baseMapper.addNowNum(id);
    }

    @Override
    public List<Dormitory> findDormitoryCan() {

        return baseMapper.findDormitoryCan();
    }
}
