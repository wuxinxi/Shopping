package com.wxx.shopping.utils;

import java.text.DecimalFormat;

/**
 * 作者：Tangren_ on 2017/3/17 18:36.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class Utils {

    private static DecimalFormat format = new DecimalFormat("#0.00");

    //约束为两位小数
    public static double checkDouble(double money) {
        return Double.valueOf(format.format(money));
    }

}
