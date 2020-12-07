package com.lmh.dormitoryService.mapper;

import com.lmh.dormitoryService.entity.Dormitory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lmh
 * @since 2020-12-01
 */
public interface DormitoryMapper extends BaseMapper<Dormitory> {

    @Update("update dormitory set now_num=now_num-1 where id=#{id}")
    Boolean reduceNowNum(Integer id);

    @Update("update dormitory set now_num=now_num+1 where id=#{id}")
    Boolean addNowNum(Integer id);

    @Select("select * from dormitory where max_num > now_num")
    List<Dormitory> findDormitoryCan();
}
