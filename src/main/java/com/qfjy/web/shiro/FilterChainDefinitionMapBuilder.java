package com.qfjy.web.shiro;

import com.qfjy.pojo.Resources;
import com.qfjy.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname FilterChainDefinitionMapBuilder
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/4 16:13
 * @Created by Administrator
 */
public class FilterChainDefinitionMapBuilder {
    @Autowired
    private ResourcesService resourcesService;

    public Map<String, String> builder(){
        Map<String,String> map=new LinkedHashMap<String,String>();

       List<Resources> list= resourcesService.selectAllByStatusAndSortNumAsc();
        for(Resources r:list){
            map.put(r.getKey(),r.getVal());
        }
        return map;
    }
    /*
        资源表 resources
        主键ID，   key,val,sortnum,status,
     */

    /*
     /login.jsp = anon
                /users/login=anon
                /css/**=anon
                /images/**=anon
                /js/**=anon
                /login/exit = logout
                /student.jsp=roles[stu]
                /teacher.jsp=roles[tea]
                /list.jsp=roles[stu,tea]
                /** = authc

     */
}
