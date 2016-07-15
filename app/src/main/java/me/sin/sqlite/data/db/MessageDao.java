package me.sin.sqlite.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sin on 2016/7/15.
 */
public class MessageDao {
    SQLiteDatabase db;

    public MessageDao(Context context) {
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        db = myOpenHelper.getReadableDatabase();
    }
}
