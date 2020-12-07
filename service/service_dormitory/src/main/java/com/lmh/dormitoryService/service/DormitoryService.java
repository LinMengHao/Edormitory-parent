package com.lmh.dormitoryService.service;

import com.lmh.dormitoryService.entity.Dormitory;
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
public interface DormitoryService extends IService<Dormitory> {

    Boolean reduceNowNum(Integer id);

    Boolean addNowNum(Integer id);


    List<Dormitory> findDormitoryCan();

}
