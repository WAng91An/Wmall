package com.mall.util;

import java.math.BigDecimal;

/**
 * Created by 王乾 on 2017/12/22.
 */
public class BigDecimalUtil {

    /**
     * 防止丢失精度的一个工具类
     */
    private BigDecimalUtil(){

    }
    //+
    public static BigDecimal add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
    //-
    public static BigDecimal sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }
    //*
     public static BigDecimal mul(double v1,double v2){
         BigDecimal b1 = new BigDecimal(Double.toString(v1));
         BigDecimal b2 = new BigDecimal(Double.toString(v2));
         return b1.multiply(b2);
     }
    ///
    public static BigDecimal div(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//4舍5入，保留两位位小数
    }

}
