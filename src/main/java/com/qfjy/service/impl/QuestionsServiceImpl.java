package com.qfjy.service.impl;

import com.qfjy.dto.QuestionsDTO;
import com.qfjy.mapper.QuestionsMapper;
import com.qfjy.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname QuestionsServiceImpl
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/2 17:13
 * @Created by Administrator
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {
     @Autowired
    private QuestionsMapper questionsMapper;

    /**
     * 一对多关联查询
     * 根据问题 查找到所有的选项
     */
    @Override
    public List<QuestionsDTO> selectAll() {
        return questionsMapper.selectAll();
    }
}
