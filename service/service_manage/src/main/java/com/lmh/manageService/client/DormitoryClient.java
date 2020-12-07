package com.lmh.manageService.client;

import com.lmh.manageService.client.impl.DormitoryClientImpl;
import com.lmh.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "service-dormitory",fallback = DormitoryClientImpl.class)
public interface DormitoryClient {
    @PostMapping("/dormitoryService/dormitory/reduceNowNum/{id}")
    public R reduceNowNum(@PathVariable("id") Integer id);
    @PostMapping("/dormitoryService/dormitory/addNowNum/{id}")
    public R addNowNum(@PathVariable("id") Integer id);
}
