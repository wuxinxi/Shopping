package com.wxx.shopping.utils;

import android.database.sqlite.SQLiteDatabase;

import com.wxx.shopping.MyApplication;
import com.wxx.shopping.db.DaoMaster;
import com.wxx.shopping.db.DaoSession;

/**
 * 作者：Tangren_ on 2017/3/23 14:35.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class GreenDaoManger {

    private final String DB_NAME = "address.db";
    private static DaoMaster master;
    private static DaoSession session;
    private static GreenDaoManger instance;

    private GreenDaoManger() {
        if (instance == null) {
            DBHelper helper = new DBHelper(MyApplication.getInstance(), DB_NAME);
            SQLiteDatabase db = helper.getWritableDatabase();
            master = new DaoMaster(db);
            session = master.newSession();
        }
    }

    public static GreenDaoManger getInstance() {
        if (instance == null) {
            synchronized (GreenDaoManger.class) {
                if (instance == null) {
                    instance = new GreenDaoManger();
                }
            }
        }

        return instance;
    }

    public static DaoSession getSession() {
        return session;
    }

}
