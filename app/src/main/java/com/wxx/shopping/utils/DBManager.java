package com.wxx.shopping.utils;

import com.wxx.shopping.bean.AddRessInfo;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/3/23 19:46.
 * 邮箱：wu_tangren@163.com
 * TODO:数据库方法类
 */

public class DBManager {

    //插入数据
    public static void insert(AddRessInfo info) {
        GreenDaoManger.getInstance().getSession().getAddRessInfoDao().insert(info);
    }

    //根据id删除
    public static void delete(long id) {
        GreenDaoManger.getInstance().getSession().getAddRessInfoDao().deleteByKey(id);
    }

    //修改信息
    public static void update(AddRessInfo info) {
        GreenDaoManger.getInstance().getSession().getAddRessInfoDao().update(info);
    }

    //降序排列
    public static List<AddRessInfo> query() {
        return GreenDaoManger.getInstance().getSession().getAddRessInfoDao().queryBuilder().orderDesc().list();
    }

}
