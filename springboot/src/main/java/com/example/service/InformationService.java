package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Information;
import com.example.mapper.InformationMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 公告信息表业务处理
 **/
@Service
public class InformationService {

    @Resource
    private InformationMapper informationMapper;

    /**
     * 新增
     */
    public void add(Information information) {
        information.setTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
        informationMapper.insert(information);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        informationMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            informationMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Information information) {
        informationMapper.updateById(information);
    }

    /**
     * 根据ID查询
     */
    public Information selectById(Integer id) {
        return informationMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Information> selectAll(Information information) {
        return informationMapper.selectAll(information);
    }

    /**
     * 分页查询
     */
    public PageInfo<Information> selectPage(Information information, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Information> list = informationMapper.selectAll(information);
        return PageInfo.of(list);
    }

    public List<Information> getRecommendList() {
        return informationMapper.getRecommendList();
    }


    public List<Information> getLeftdata() {
        return informationMapper.getLeftdata();
    }
}
