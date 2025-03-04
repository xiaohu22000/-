package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 课程信息表业务处理
 **/
@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 新增
     */
    public void add(Course course) {
        course.setTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
        courseMapper.insert(course);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            courseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Course course) {
        courseMapper.updateById(course);
    }

    /**
     * 根据ID查询
     */
    public Course selectById(Integer id) {
        return courseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Course> selectAll(Course course) {
        return courseMapper.selectAll(course);
    }

    /**
     * 分页查询
     */
    public PageInfo<Course> selectPage(Course course, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseMapper.selectAll(course);
        return PageInfo.of(list);
    }

    public Course getRecommend() {
        return courseMapper.getRecommend();
    }


    public List<Course> getRecommendList() {
        return  courseMapper.getRecommendList();
    }

    public List<Course> selectTop8() {
        return courseMapper.selectTop8();
    }

    public List<Course> getRecommendVideo() {
        return courseMapper.getRecommendVideo();
    }

    public List<Course> selectTop8Video() {
        return courseMapper.selectTop8Video();
    }

    public List<Course> getRecommendText() {
        return courseMapper.getRecommendText();
    }

    public List<Course> selectTop8Text() {
        return courseMapper.selectTop8Text();
    }
}
