package com.qfjy.service.impl;

import com.qfjy.mapper.UsersMapper;
import com.qfjy.pojo.Users;
import com.qfjy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Classname UsersServiceImpl
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/3 14:48
 * @Created by Administrator
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * 根据用户名查询用户对象是否存在。
     * @param username
     * @return Users
     */
    @Override
    public Users selectByUsername(String username) {
        return usersMapper.selectByUsername(username);
    }

    /**
     * 根据用户名查询所有的角色信息
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> selectRnamesByUserName(String username) {
        return usersMapper.selectRnamesByUserName(username);
    }

    /**
     * 查询所有角色的方法
     */
    @Override
    public Set<String> selectRolesAllRnames() {
        return usersMapper.selectRolesAllRnames();
    }
}
