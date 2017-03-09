package com.giousa.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.giousa.sqlitedemo.sql.MySqliteHelper;
import com.giousa.sqlitedemo.utils.DBManager;

public class MainActivity extends AppCompatActivity {

    Button mCreateDB;
    private MySqliteHelper mMySqliteHelper;
    private SQLiteDatabase mReadableDatabase;
    private SQLiteDatabase mWritableDatabase;//创建或打开数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();

    }

    private void initView() {
        mMySqliteHelper = DBManager.getInstance(this);
    }


    private void initListener() {
        mCreateDB = (Button) findViewById(R.id.btn_create);

        mCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //默认情况下,打开的都是可读可写数据库,只是当内存已满或权限限制时,Read打开的是只读数据库
//                mReadableDatabase = mMySqliteHelper.getReadableDatabase();
                mWritableDatabase = mMySqliteHelper.getWritableDatabase();
            }
        });

    }


}
