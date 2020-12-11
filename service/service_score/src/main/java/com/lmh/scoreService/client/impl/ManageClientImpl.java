package com.lmh.scoreService.client.impl;

import com.lmh.scoreService.client.ManageClient;
import com.lmh.utils.R;
import org.springframework.stereotype.Component;

@Component
public class ManageClientImpl implements ManageClient {
  @Override
  public R getByBuildId(Integer buildId) {
    return R.error().message("连接超时");
  }

  @Override
  public R getStudentsByDormitoryId(Integer dormitoryId) {
    return R.error().message("连接超时");
  }

  @Override
  public R findByPhoneNumAndName(String name, String phoneNum) {
    return R.error().message("连接超时");
  }

}
