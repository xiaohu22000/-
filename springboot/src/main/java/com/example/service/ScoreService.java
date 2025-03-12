package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Score;
import com.example.entity.Score;
import com.example.entity.Scoreorder;
import com.example.mapper.ScoreMapper;
import com.example.mapper.ScoreorderMapper;
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
public class ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private ScoreorderMapper scoreorderMapper;

    /**
     * 新增
     */
    public void add(Score score) {
        score.setTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
        scoreMapper.insert(score);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        scoreMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            scoreMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Score score) {
        scoreMapper.updateById(score);
    }

    /**
     * 根据ID查询
     */
    public Score selectById(Integer id) {

        Score score= scoreMapper.selectById(id);
        //校验当前用户有没有做积分兑换
        Account currentUser= TokenUtils.getCurrentUser();
        Scoreorder scoreorder=new Scoreorder();
        scoreorder.setScoreId(score.getId());
        scoreorder.setUserId(currentUser.getId());
        List<Scoreorder> scoreorders=scoreorderMapper.selectAll(scoreorder);
        if (ObjectUtil.isEmpty(scoreorders)){
            score.setFile("");
        }
        return score;
    }

    /**
     * 查询所有
     */
    public List<Score> selectAll(Score score) {
        return scoreMapper.selectAll(score);
    }

    /**
     * 分页查询
     */
    public PageInfo<Score> selectPage(Score score, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Score> list = scoreMapper.selectAll(score);
        return PageInfo.of(list);
    }

    public List<Score> getRecommendList() {
        return  scoreMapper.getRecommendList();
    }

    public List<Score> selectTop8() {
        return scoreMapper.selectTop8();
    }
}
