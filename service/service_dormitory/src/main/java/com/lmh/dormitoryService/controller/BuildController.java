package com.lmh.dormitoryService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lmh.base.handler.LmhException;
import com.lmh.dormitoryService.client.ManageClient;
import com.lmh.dormitoryService.entity.Build;
import com.lmh.dormitoryService.service.BuildService;
import com.lmh.dormitoryService.vo.BuildQuery;
import com.lmh.utils.R;
import com.lmh.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "楼字管理")
@CrossOrigin
@RestController
@RequestMapping("/dormitoryService/build")
public class BuildController {
    @Autowired
    private BuildService buildService;
    @Autowired
    private ManageClient manageClient;

    @ApiOperation(value = "楼字查询")
    @PostMapping("findAll/{current}/{size}")
    public R findAll(@PathVariable("current") Long current,@PathVariable("size") Long size,
                     @RequestBody(required = false) BuildQuery buildQuery){

        Page<Build> page=new Page<>(current,size);
        QueryWrapper<Build> wrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(buildQuery.getName())){
            wrapper.like("name",buildQuery.getName());
        }
        if(!StringUtils.isEmpty(buildQuery.getAddress())){
            wrapper.like("address",buildQuery.getAddress());
        }
        wrapper.orderByDesc("create_time");

        buildService.page(page,wrapper);
        long total = page.getTotal();
        List<Build> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    @ApiOperation(value = "根据id查楼字信息,把对应宿舍管理员查询出")
    @GetMapping("getMembersById/{id}")
    public R getById(@PathVariable Integer id){
        R r = manageClient.getByBuildId(id);
        if(r.getCode()==999){
            throw new LmhException(ResultCode.COMMON_FAIL.getCode(),"获取宿舍楼管理员信息失败，服务器熔断");
        }
        Build build = buildService.getById(id);
        return r.data("build",build);
    }
    @ApiOperation(value = "根据id查楼字信息")
    @GetMapping("getBuildById/{id}")
    public R getBuildById(@PathVariable Integer id){
        Build build = buildService.getById(id);
        return R.ok().data("build",build);
    }
    @ApiOperation(value = "增加楼字")
    @PostMapping("addBuild")
    public R addBuild(@RequestBody Build build){
        boolean flag = buildService.save(build);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "修改楼字信息")
    @PostMapping("updateBuild")
    public R updateBuild(@RequestBody Build build){
        boolean b = buildService.updateById(build);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "删除楼字")
    @DeleteMapping("deleteBuild/{id}")
    public R deleteBuild(@PathVariable("id")Integer id){
        boolean b = buildService.removeById(id);
        if(b){
            return R.ok();

        }else {
            return R.error();
        }
    }
    @ApiOperation(value = "查询所有build")
    @PostMapping("getAllBuild")
    public R getAllBuild(){
        List<Build> list = buildService.list();
        return R.ok().data("buildList",list);
    }
}

