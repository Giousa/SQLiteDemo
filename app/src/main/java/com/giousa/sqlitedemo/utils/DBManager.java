package com.giousa.sqlitedemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.giousa.sqlitedemo.sql.MySqliteHelper;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/3/9
 * Time:下午6:29
 */

public class DBManager {

    private static MySqliteHelper mySqliteHelper;
    public static MySqliteHelper getInstance(Context context){
        if(mySqliteHelper == null){
            mySqliteHelper = new MySqliteHelper(context);
        }
        return mySqliteHelper;
    }


    public static void execSQL(SQLiteDatabase db,String sql){

        if(db != null){
            if(sql != null && !"".equals(sql)){
                db.execSQL(sql);
            }

        }

    }
}
