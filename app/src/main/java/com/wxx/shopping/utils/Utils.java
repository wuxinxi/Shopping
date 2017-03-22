package com.wxx.shopping.utils;

import java.text.DecimalFormat;

/**
 * 作者：Tangren_ on 2017/3/17 18:36.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class Utils {

    public static final String info = "[{\"name\":\"吴新喜\",\"phone\":\"15012472465\",\"address\":\"湖南省湘潭市雨湖区宝马西路湖南软件职业学院\",\"isCheck\":false},{\"name\":\"唐人\",\"phone\":\"18373212075\",\"address\":\"安徽省亳州市谯城区牛集镇安留街\",\"isCheck\":false},{\"name\":\"吴新喜\",\"phone\":\"15012472465\",\"address\":\"广东省深圳市宝安区西乡步行街河西四坊405-502\",\"isCheck\":false},{\"name\":\"丁晓娇\",\"phone\":\"15173243121\",\"address\":\"湖南省长沙市福田区\",\"isCheck\":false},{\"name\":\"吴新喜\",\"phone\":\"15173243121\",\"address\":\"广东省深圳市南山区科技园科苑西路9号27栋1楼\",\"isCheck\":true}]\n";

    private static DecimalFormat format = new DecimalFormat("#0.00");

    //约束为两位小数
    public static double checkDouble(double money) {
        return Double.valueOf(format.format(money));
    }


}
