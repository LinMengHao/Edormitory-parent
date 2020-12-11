package com.lmh.scoreService.service;

import com.lmh.scoreService.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lmh
 * @since 2020-12-10
 */
public interface ScoreService extends IService<Score> {

    Integer addScore(Score score);
}
