package com.giousa.sqlitedemo.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.giousa.sqlitedemo.utils.Constant;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/3/9
 * Time:下午6:21
 */

public class MySqliteHelper extends SQLiteOpenHelper {


    /**
     *
     * @param context 上下文
     * @param name    数据库名称
     * @param factory 游标工厂
     * @param version 当前版本 >=1
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context, Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }

    /**
     *
     * @param sqLiteDatabase 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("---onCreate---");
        String sql = "create table "+Constant.TABLE_NAME+"("+Constant._ID+" Integer primary key,"
                +Constant.NAME+" varchar(10),"+Constant.AGE+" Integer)";
    }

    /**
     *
     * @param sqLiteDatabase  数据库对象
     * @param i  旧版本
     * @param i1 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("---onUpgrade---");

    }


    /**
     *
     * @param db  数据库对象
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        System.out.println("---onOpen---");
    }
}
