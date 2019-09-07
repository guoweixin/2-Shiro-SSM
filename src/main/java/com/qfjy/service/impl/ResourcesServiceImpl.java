package com.qfjy.service.impl;

import com.qfjy.mapper.ResourcesMapper;
import com.qfjy.pojo.Resources;
import com.qfjy.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname ResourcesServiceImpl
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/4 16:35
 * @Created by Administrator
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;
    /**
     * 查询所有的资源权限 状态有效、且按sortnum升序排序
     */
    @Override
    public List<Resources> selectAllByStatusAndSortNumAsc() {
        return resourcesMapper.selectAllByStatusAndSortNumAsc();
    }
}
