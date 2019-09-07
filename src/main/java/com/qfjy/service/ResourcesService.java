package com.qfjy.service;

import com.qfjy.pojo.Resources;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResourcesService {

    /**
     * 查询所有的资源权限 状态有效、且按sortnum升序排序
     */
    List<Resources> selectAllByStatusAndSortNumAsc();
}
