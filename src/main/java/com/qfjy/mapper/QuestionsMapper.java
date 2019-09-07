package com.qfjy.mapper;

import com.qfjy.dto.QuestionsDTO;
import com.qfjy.pojo.Questions;

import java.util.List;

public interface QuestionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);

    /**
     * 一对多关联查询
     * 根据问题 查找到所有的选项
     */
    List<QuestionsDTO> selectAll();
}