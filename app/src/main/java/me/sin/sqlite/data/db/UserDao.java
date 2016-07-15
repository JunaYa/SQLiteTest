package me.sin.sqlite.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import me.sin.sqlite.data.modle.User;

/**
 * Created by sin on 2016/7/15.
 */
public class UserDao {
    SQLiteDatabase db ;

    public UserDao(Context context) {
        MyOpenHelper helper = new MyOpenHelper(context);
        db = helper.getReadableDatabase();
    }

    public void insert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",user.name);
        db.insert("user",null,contentValues);
    }
}
