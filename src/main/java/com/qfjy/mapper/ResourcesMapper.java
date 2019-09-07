package com.qfjy.mapper;

import com.qfjy.pojo.Resources;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);

    /**
     * 查询所有的资源权限 状态有效、且按sortnum升序排序
     */
    @Select("  select * from resources where `status`=1 ORDER BY sortnum asc ")
    List<Resources> selectAllByStatusAndSortNumAsc();
}