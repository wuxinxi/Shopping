package com.wxx.shopping.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.wxx.shopping.db.AddRessInfoDao;
import com.wxx.shopping.db.DaoMaster;

/**
 * 作者：Tangren_ on 2017/3/23 14:26.
 * 邮箱：wu_tangren@163.com
 * TODO:数据库升级
 */

public class DBHelper extends DaoMaster.OpenHelper {


    public DBHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, AddRessInfoDao.class);
    }
}
