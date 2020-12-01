package com.lmh.manageService.controller;


import com.lmh.manageService.entity.Member;
import com.lmh.manageService.service.MemberService;
import com.lmh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lmh
 * @since 2020-12-01
 */
@Api(tags = "宿舍管理员")
@CrossOrigin
@RestController
@RequestMapping("/manageService/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "根据id查询宿管信息")
    @PostMapping("getByBuildId/{buildId}")
    public R getByBuildId(@PathVariable Integer buildId){
        List<Member> members=memberService.getByBuildId(buildId);
        return R.ok().data("members",members);
    }
}

