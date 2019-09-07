package com.qfjy.mapper;

import com.qfjy.pojo.Users;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    //根据用户名查询用户对象是否存在。
    @Select(" select * from users where username=#{username}")
    Users selectByUsername(String username);

    /**
     * 根据用户名查询所有的角色信息
     */
    @Select(" select r.rname from users u " +
            " LEFT JOIN users_roles ur ON u.id=ur.uid " +
            " LEFT JOIN roles r ON r.id=ur.rid" +
            " where u.username=#{username}")
    Set<String> selectRnamesByUserName(String username);

    /**
     * 查询所有角色的方法
     */
    @Select(" select rname from roles where `status`=1 ")
    Set<String> selectRolesAllRnames();

}