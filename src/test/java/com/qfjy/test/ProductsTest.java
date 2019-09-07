package com.qfjy.test;

import com.qfjy.pojo.Products;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname ProductsTest
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/6 9:54
 * @Created by Administrator
 */
public class ProductsTest {

    /**
     * SolrJ提供两个有用的接口，UpdateResponse 和 QueryResponse，
     * 它们可以很方便的处理特定域的对象,可以使您的应用程序更容易被理解。
     * SolrJ支持通过@Field注解隐式转换文档与任何类。
     */
    /**
     * 在solr中，索引库中都会存在一个唯一键，
     * 如果一个Document的id存在，则执行修改操作，如果不存在，则执行添加操作。
     */
    @Test
    public void addOrUpdate() throws IOException, SolrServerException {
        //1得到Solr服务地址
        String solrUrl="http://localhost:9080/solr/core_jd";
        //2创建客户端连接
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl).build();
        //3 完成增加或 修改

        Products p=new Products();
        p.setPid("9999998");
        p.setCatalogName("学习测试");
        p.setPname("在测试添加一个商品数据");
        p.setDescription("这是我的测试内容描述");
        p.setPicture("1.jpg");
        p.setPrice(12.5);
        //4将该对象插入到Solr索引库中
        UpdateResponse updateResponse=solrClient.addBean(p);
        System.out.println("Solr J数据操作成功");
        //5 提交事务
        solrClient.commit();
        //6客户端关闭
        solrClient.close();;
    }

    /**
     * 根据条件进行删除Solr索引库数据
     */
    @Test
    public void delIndex() throws IOException, SolrServerException {
        //1得到Solr服务地址
        String solrUrl="http://localhost:9080/solr/core_jd";
        //2创建客户端连接
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl).build();

        //3执行删除接口
        String id="9999998";
      //  UpdateResponse response=solrClient.deleteById(id); //根据ID进行删除

        String q="*:*";//Solr客户端的q 查询语法
        solrClient.deleteByQuery(q); //清空Solr索引库数据
        System.out.println("Solr J数据操作成功");
        //4提交事务
        solrClient.commit();
        //5客户端关闭
        solrClient.close();;
    }

    /**
     * 查询
     */
    @Test
    public void search() throws IOException, SolrServerException {
        //1得到Solr服务地址
        String solrUrl="http://localhost:9080/solr/core_jd";
        //2创建客户端连接
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl).build();
        //3查询的对象接口
        SolrQuery solrQuery=new SolrQuery("*:*");
        QueryResponse queryResponse=solrClient.query(solrQuery);
        //4得到查询的结果
       List<Products> list= queryResponse.getBeans(Products.class);

       for(Products p:list){
           System.out.println(p.getPid()+"\t"+p.getPname());
       }
    }


    /**
     * 复杂查询
     */
    @Test
    public void complexSearch() throws IOException, SolrServerException {
        //1得到Solr服务地址
        String solrUrl="http://localhost:9080/solr/core_jd";
        //2创建客户端连接
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl).build();
        //3查询的对象接口
       // SolrQuery solrQuery=new SolrQuery("prod_pname:手机");
        SolrQuery query=new SolrQuery();
        //设置q
        query.set("q","手机音乐");

        // 设置 fq 根据前一条件，根据类别进行过滤   多个 add FQ
        query.addFilterQuery("prod_catalog_name:手机饰品 or prod_catalog_name:幽默杂货");//类别筛选  fq....


       // query.addFilterQuery("prod_price:[10 TO *]"); //价格筛选

        //sort 排序  按价格 降序 prod_price desc
        query.addSort("prod_price", SolrQuery.ORDER.desc);

        /*
            mysql :
            limit  offset,[rows];
            offset:偏移量（0开始）
            rows:返回的最大记录数
              offset: (currtPage-1)*pageSize
                rows: pageSize
         */
        //分页  start rows
        query.setStart(0);  // offset
        query.setRows(6);   // rows

        // fl 回显设置
        query.setFields("prod_pname","id");

        //df 默认域设置
        query.set("df","prod_pname");

        // 设置 高亮功能
        query.setHighlight(true);//启动高亮
        query.addHighlightField("prod_pname");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");


        QueryResponse queryResponse=solrClient.query(query);


        //4得到查询的结果
        List<Products> list= queryResponse.getBeans(Products.class);


        //高亮数据 替换 原文的标题内容
        Map<String, Map<String, List<String>>> map= queryResponse.getHighlighting();



        //总记录数
      long count=  queryResponse.getResults().getNumFound();
        System.out.println("查询的总记录数是："+count);
        for(Products p:list){

        if(map!=null) {
            Map<String, List<String>> map1 = map.get(p.getPid());
            List<String> list1 = map1.get("prod_pname");
            String resultPname = list1.get(0);
            p.setPname(resultPname);
        }
            System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPrice());
        }
    }

}
