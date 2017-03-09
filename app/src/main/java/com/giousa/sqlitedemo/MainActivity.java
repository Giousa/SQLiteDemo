package com.giousa.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.giousa.sqlitedemo.sql.MySqliteHelper;
import com.giousa.sqlitedemo.utils.Constant;
import com.giousa.sqlitedemo.utils.DBManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MySqliteHelper mMySqliteHelper;
    private SQLiteDatabase mReadableDatabase;
    private SQLiteDatabase mWritableDatabase;//创建或打开数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initView();

    }

    private void initView() {
        mMySqliteHelper = DBManager.getInstance(this);
    }


    @OnClick({R.id.btn_create, R.id.btn_insert, R.id.btn_delete, R.id.btn_update, R.id.btn_query, R.id.btn_insert_api, R.id.btn_delete_api, R.id.btn_update_api, R.id.btn_query_api})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                createDB();
                break;
            case R.id.btn_insert:
                insertDB();
                break;
            case R.id.btn_delete:
                deleteDB();
                break;
            case R.id.btn_update:
                updateDB();
                break;
            case R.id.btn_query:
                queryDB();
                break;
            case R.id.btn_insert_api:
                insertDBAPI();
                break;
            case R.id.btn_delete_api:
                deleteDBAPI();
                break;
            case R.id.btn_update_api:
                updateDBAPI();
                break;
            case R.id.btn_query_api:
                queryDBAPI();
                break;
        }
    }


    private void createDB() {
        //默认情况下,打开的都是可读可写数据库,只是当内存已满或权限限制时,Read打开的是只读数据库
        //mReadableDatabase = mMySqliteHelper.getReadableDatabase();
        mWritableDatabase = mMySqliteHelper.getWritableDatabase();
    }


    //-------通过SQL语句对数据进行增删改查-------

    private void insertDB() {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();

        for (int i = 0; i < 5; i++) {
            String sql = "insert into " + Constant.TABLE_NAME + " values(" + i + ",'giousa',20)";
            DBManager.execSQL(db, sql);
            System.out.println("------插入数据成功!------");
        }

        db.close();
    }

    private void deleteDB() {

        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        String sql = "delete from " + Constant.TABLE_NAME + " where " + Constant._ID + "=2";
        DBManager.execSQL(db, sql);
        db.close();
        System.out.println("-----数据删除成功!-----");

    }

    private void updateDB() {

        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        String sql = "update " + Constant.TABLE_NAME + " set " + Constant.NAME + "='不笑猫' where " + Constant._ID + "=1";
        DBManager.execSQL(db, sql);
        db.close();
        System.out.println("-----数据更新成功!-----");

    }

    private void queryDB() {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();

        String sql = "select * from "+Constant.TABLE_NAME;
        Cursor cursor = DBManager.selectBySQL(db, sql, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            System.out.println("查询数据成功  name = "+name);
        }

        db.close();
    }

    //-------通过API对数据进行增删改查-------

    private void insertDBAPI() {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();

        for (int i = 0; i < 5; i++) {
            ContentValues cv = new ContentValues();
            cv.put(Constant._ID,10+i);
            cv.put(Constant.NAME,"Giou"+i);
            cv.put(Constant.AGE,12+i);
            long insert = db.insert(Constant.TABLE_NAME, null, cv);
            if(insert > 0){
                System.out.println("-----插入数据成功-----");
            }else{
                System.out.println("-----插入数据失败-----");
            }
        }

        db.close();
    }

    private void deleteDBAPI() {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        int delete = db.delete(Constant.TABLE_NAME, Constant._ID + ">?", new String[]{"4"});
        if(delete > 0){
            System.out.println("-----删除数据成功-----");
            System.out.println("delete count = "+delete);
        }else{
            System.out.println("-----删除数据失败-----");
        }

        db.close();
    }

    private void updateDBAPI() {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constant.NAME,"木之本樱");
//        db.update(Constant.TABLE_NAME,cv,Constant._ID+"=3",null);
        int update = db.update(Constant.TABLE_NAME, cv, Constant._ID + "=?", new String[]{"3"});
        System.out.println("-----修改数据成功-----");
        System.out.println("update value = "+update);
        db.close();
    }

    private void queryDBAPI() {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();

        Cursor cursor = db.query(Constant.TABLE_NAME, null, Constant._ID + ">?", new String[]{"0"}, null, null, null);
        //mCursor.getString(mCursor.getColumnIndex(NotesDB.CONTENT))

        while (cursor.moveToNext()){
            int columnIndex = cursor.getColumnIndex(Constant.NAME);
            String name = cursor.getString(columnIndex);
            System.out.println("columnIndex = " + columnIndex + "  name = "+name);
        }

        db.close();
    }


}
