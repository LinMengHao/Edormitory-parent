package com.lmh.scoreService.client;

import com.lmh.scoreService.client.impl.ManageClientImpl;
import com.lmh.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-manage",fallback = ManageClientImpl.class)
@Component
public interface ManageClient {
    @GetMapping("/manageService/member/getByBuildId/{buildId}")
    public R getByBuildId(@PathVariable("buildId") Integer buildId);

    @GetMapping("/manageService/student/getStudentsByDormitoryId/{dormitoryId}")
    public R getStudentsByDormitoryId(@PathVariable("dormitoryId") Integer dormitoryId);

    @PostMapping("/manageService/member/findByPhoneNumAndName")
    public R findByPhoneNumAndName(
            @RequestParam("name") String name, @RequestParam("phoneNum") String phoneNum);
}
