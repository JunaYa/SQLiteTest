package me.sin.sqlite.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sin on 2016/7/15.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    /**
     * @param context "Message.db"  --数据库名称
     *                null  --CursorFactory
     *                3 --version code
     */
    public MyOpenHelper(Context context) {
        super(context, DConst.DATABASE_NAME, null, DConst.CURRENT_VERSION);
    }

    // 数据创建时执行
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("=======", "onCreate doing");
        sqLiteDatabase.execSQL(DConst.CREATE_TABLE_NOTE);
    }

    /**
     * 当数据库版本升级时执行此方法，一般处理，表中添加字段，或者新建表
     *
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("=======", "onUpgrade doing");
        Log.d("=======", "oldVersion="+oldVersion);
        switch (oldVersion) {
            case 1:
                sqLiteDatabase.execSQL(DConst.ALTER_TABLE_NOTE);
            case 2:
                sqLiteDatabase.execSQL(DConst.CREATE_TABLE_USER);
        }
    }
}
