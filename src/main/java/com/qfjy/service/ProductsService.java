package com.qfjy.service;

import com.qfjy.utils.PageUtils;

/**
 * 商品主页的Service
 */
public interface ProductsService {

    /**
     * 电商主页 查询（模仿京东）
     * @Param keyword 查询关键字
     * @Param catalogName 类别名称
     * @Param priceStr 价格
     * @Param psort 排序标识数  1 升序  2降序
     * @Param currtPage 当前页数
     * @Param pageSize 页大小  默认12
     * @return
     */
    public PageUtils search(String keyword,String catalogName,String priceStr,String psort,Integer currtPage,Integer pageSize)
            throws Exception;
}
