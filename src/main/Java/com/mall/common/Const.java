package com.mall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by 王乾 on 2017/12/11.
 */
public class Const {

    public static  final  String CURRENT_USER = "currentUser";
    public static  final  String EMAIL =  "email";
    public static  final  String USERNAME = "username";

    public interface ProductListOrderBy{
       Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public interface Cart{
        int CHECKED = 1;//购物车为选中状态
        int UN_CHECKED = 0; //未选中状态
        String  LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";//限制失败
        String  LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";//限制成功
    }

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    public  enum  ProductStatusEnum{
        ON_SALE(1,"在线");
        private  String value;
        private  int code;
        ProductStatusEnum(int code,String value){
            this.code   = code;
            this.value  = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

}
