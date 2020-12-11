package com.lmh.scoreService.service.impl;

import com.lmh.base.handler.LmhException;
import com.lmh.scoreService.entity.Score;
import com.lmh.scoreService.mapper.ScoreMapper;
import com.lmh.scoreService.service.ScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmh.utils.ResultCode;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lmh
 * @since 2020-12-10
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Override
    public Integer addScore(Score score) {
        int insert = baseMapper.insert(score);
        if(insert<=0){
            throw new LmhException(ResultCode.SAVE_ERROR.getCode(),ResultCode.SAVE_ERROR.getMessage());
        }
        return score.getId();
    }
}
