package com.lmh.manageService.client.impl;

import com.lmh.manageService.client.DormitoryClient;
import com.lmh.utils.R;
import org.springframework.stereotype.Component;

@Component
public class DormitoryClientImpl implements DormitoryClient {
    @Override
    public R reduceNowNum(Integer id) {
        return R.error().message("连接超时");
    }

    @Override
    public R addNowNum(Integer id) {
        return R.error().message("连接超时");
    }
}
