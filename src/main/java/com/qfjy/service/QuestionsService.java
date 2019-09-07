package com.qfjy.service;

import com.qfjy.dto.QuestionsDTO;

import java.util.List;

public interface QuestionsService {

    /**
     * 一对多关联查询
     * 根据问题 查找到所有的选项
     */
    List<QuestionsDTO> selectAll();
}
