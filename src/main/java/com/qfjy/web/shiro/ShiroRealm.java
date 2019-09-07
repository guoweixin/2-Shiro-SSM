package com.qfjy.web.shiro;

import com.qfjy.pojo.Users;
import com.qfjy.service.UsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Classname ShiroRealm
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/3 11:13
 * @Created by Administrator
 */
public class ShiroRealm  extends AuthorizingRealm {
    @Autowired
    private UsersService usersService;


    /**
     * 认证
     * 1、该方法什么情况下会被调用
     *      currentUser.login(token)
     * 2、该方法的入参是什么数据
     *   UsernamePasswordToken
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken= (UsernamePasswordToken) token;

        //1 得到用户名输入的用户名
        String username=upToken.getUsername();
        //2 判断当前用户在数据库中是否存在
       Users users=usersService.selectByUsername(username);
        //3如果用户名不存在  UnknownAccountException
        if(users==null){
            throw new UnknownAccountException("用户名不存在");
        }
        //4如果用户的状态 锁定  LockedAccountException   status=0  1正常
        if(users.getStatus()==0){
            throw new LockedAccountException("该帐户已被锁定");
        }
        //5 密码的比较（前台的密码=数据库中查询的密码）  Shiro内部来完成的。
        // Object principal, Object credentials, String realmName
        /*
        身份验证：一般需要提供如身份 ID 等一些标识信息来表明登录者的身份，如提供 email，用户名/密码来证明。
        在 shiro 中，用户需要提供 principals （身份）和 credentials（证  明）给 shiro，从而应用能验证用户身份：
        •  principals：身份，即主体的标识属性，可以是任何属性，如用户名、  邮箱等，唯一即可。
        一个主体可以有多个 principals，但只有一个  Primary principals，一般是用户名/邮箱/手机号。
        •credentials：证明/凭证，即只有主体知道的安全值，如密码/数字证  书等。
        •	最常见的 principals 和 credentials 组合就是用户名/密码了

         */
        Object principal=username;
        Object credentials=users.getPassword();//数据库查询出的密码
        ByteSource credentialsSalt=ByteSource.Util.bytes(username); //加盐主体
        //AuthenticationInfo info=new SimpleAuthenticationInfo(principal,credentials,super.getName());
        AuthenticationInfo info =new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,super.getName());
        return info;
    }

    /**
     * 授权
     * 1、该方法什么情况下会被调用？
     *      1.1当访问需要角色权限时访问
     *      1.2如果找到该角色，就不会再第二次调用。
     * 2、该方法的入参是什么数据？
     *     主体信息（用户名）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       //1 得到用户名信息
        String username= (String) principals.getPrimaryPrincipal();
        //2 查询数据库，根据用户名称，查询该用户拥有哪些角色
         Set<String> roles=  usersService.selectRnamesByUserName(username);

         //3 管理员拥有所有的角色（特殊权限）
        if(roles.contains("admin")){
            roles=usersService.selectRolesAllRnames();
        }
        AuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        return info;
    }


    /**
     * 注册功能时需要使用的代码
     * @param args
     */
    public static void main(String[] args) {
        //注册时存入的数据
        //对数据库的密码按照相应规则加密
        /**
         * hashAlgorithmName 加密码名称
         * credentials 要加密的密码
         * hashIterations 加密的次数
         */
        Object credentials="123456";
        String hashAlgorithmName ="MD5";
        String username="maoshuai";  //用户名
        Object salt=ByteSource.Util.bytes(username); ;
        int hashIterations=1902;  //加密次数
        Object result=new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);


    }
}
