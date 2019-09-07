package com.qfjy.service;

import com.qfjy.pojo.Users;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface UsersService {


    /**
     * 根据用户名查询用户对象是否存在。
     * @param username
     * @return Users
     */
    Users selectByUsername(String username);

    /**
     * 根据用户名查询所有的角色信息
     * @param username
     * @return
     */
    Set<String> selectRnamesByUserName(String username);

    /**
     * 查询所有角色的方法
     */
    Set<String> selectRolesAllRnames();
}
