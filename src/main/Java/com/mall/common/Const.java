package com.mall.common;

/**
 * Created by 王乾 on 2017/12/11.
 */
public class Const {

    public static  final  String CURRENT_USER = "currentUser";
    public static  final  String EMAIL =  "email";
    public static  final  String USERNAME = "username";
    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
