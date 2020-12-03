package com.lmh.dormitoryService.client.impl;

import com.lmh.dormitoryService.client.ManageClient;
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


}
