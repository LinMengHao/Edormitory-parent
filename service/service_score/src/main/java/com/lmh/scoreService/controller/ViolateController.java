package com.lmh.scoreService.controller;


import com.lmh.scoreService.entity.Violate;
import com.lmh.scoreService.service.ViolateService;
import com.lmh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lmh
 * @since 2020-12-10
 */
@Api("违章")
@CrossOrigin
@RestController
@RequestMapping("/scoreService/violate")
public class ViolateController {

    @Autowired
    private ViolateService violateService;
    @ApiOperation("保存违章")
    @PostMapping("addViolate")
    public R addViolate(@RequestBody Violate violate){
        boolean save = violateService.save(violate);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

