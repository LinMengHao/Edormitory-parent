package com.lmh.scoreService.controller;

import com.lmh.base.handler.LmhException;
import com.lmh.scoreService.client.ManageClient;
import com.lmh.scoreService.entity.Score;
import com.lmh.scoreService.service.ScoreService;
import com.lmh.utils.R;
import com.lmh.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前端控制器
 *
 * @author lmh
 * @since 2020-12-10
 */
@Api("评分系统")
@CrossOrigin
@RestController
@RequestMapping("/scoreService/score")
public class ScoreController {

  @Autowired private ScoreService scoreService;
  @Autowired private ManageClient manageClient;

  @ApiOperation("保存评分")
  @PostMapping("addScore")
  public R addScore(@RequestBody Score score) {
    R r = manageClient.findByPhoneNumAndName(score.getAuthor(), score.getPhoneNum());
    if(r.getCode()==999){
      throw new LmhException(ResultCode.COMMON_FAIL.getCode(), "姓名电话不匹配，请本人操作");
    }
    Integer id = scoreService.addScore(score);
      return R.ok().data("id",id);
  }

  @ApiOperation("根据id查询")
  @GetMapping("findById/{id}")
  public R findById(@PathVariable("id") Integer id) {
    Score score = scoreService.getById(id);
    return R.ok().data("score", score);
  }

  @ApiOperation("修改")
  @PostMapping("updateScore")
  public R updateScore(@RequestBody Score score) {
    boolean b = scoreService.updateById(score);
    if (b) {
      return R.ok();
    } else {
      return R.error();
    }
  }
}
