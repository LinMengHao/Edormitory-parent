package com.lmh.dormitoryService.client;

import com.lmh.dormitoryService.client.impl.ManageClientImpl;
import com.lmh.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-manage",fallback = ManageClientImpl.class)
@Component
public interface ManageClient {
    @GetMapping("/manageService/member/getByBuildId/{buildId}")
    public R getByBuildId(@PathVariable("buildId") Integer buildId);
}
