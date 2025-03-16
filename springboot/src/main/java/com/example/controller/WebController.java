package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基础前端接口
 */
@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;
    @Resource
    private ScoreService scoreService;
    @Resource
    private InformationService informationService;

    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        }
        if (RoleEnum.USER.name().equals(account.getRole())){
            account=userService.login(account);
        }
        return Result.success(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        if (RoleEnum.USER.name().equals(account.getRole())){
            userService.register(account);
        }
        return Result.success();
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if(RoleEnum.USER.name().equals(account.getRole())){
            userService.updatePassword(account);
        }
        return Result.success();
    }

    @GetMapping("/getBar")
    public Result getbar(){
        Map<String,Object> resultMap=new HashMap<>();
        List<String> xlist=new ArrayList<>();
        List<Long> ylist=new ArrayList<>();

        xlist.add("视频课程");
        xlist.add("图文课程");
        xlist.add("积分兑换课程课程");
        xlist.add("下载课程");

        List<Course> courses= courseService.selectAll(new Course());
        ylist.add(courses.stream().filter(x ->"VIDEO".equals(x.getType())).count());
        ylist.add(courses.stream().filter(x ->"TEXT".equals(x.getType())).count());
        ylist.add((long)scoreService.selectAll(new Score()).size());
        ylist.add((long)informationService.selectAll(new Information()).size());

        resultMap.put("text","平台所有资料总数统计(柱状图)");
        resultMap.put("subText","统计维度：是否为会员身份");
        resultMap.put("xAxix",xlist);
        resultMap.put("yAxix",ylist);
        return Result.success(resultMap);
    }
}
