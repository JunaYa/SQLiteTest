package me.sin.sqlite.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.sin.sqlite.data.modle.Note;

/**
 * Created by sin on 2016/7/15.
 */
public class NoteDao {

    SQLiteDatabase db;

    public NoteDao(Context context) {
        MyOpenHelper helper = new MyOpenHelper(context);
        db = helper.getReadableDatabase();
    }


    public void insert(Note note) {
//        // 拼接的方法
        String sql = "insert into Notes(title,content,date,tag) values('" + note.title + "','" + note.content + "','" + note.date + "','"+note.tag+"')";
        db.execSQL(sql);

//        使用sql占位符形式
//        String sql = "insert into Notes(?,?,?,?)";
//        db.execSQL(sql, new Object[]{null,note.title, note.content, note.date});
        //使用系统自带的方法
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("title", note.title);
//        contentValues.put("content", note.content);
//        contentValues.put("date", note.date);
////        contentValues.put("tag", note.tag);
//        db.insert("Notes", null, contentValues);
    }

    public void update(Note note) {
        //拼接方法
//        String sql = "update Notes set title = '"+note.content+"'" + "where id = '"+note.id+"'";
//        db.execSQL(sql);
        //使用占位符
//        String sql = "update Notes set title = ? where id = ? ";
//        db.execSQL(sql,new Object[]{note.title,note.id});

        //使用系统自带的方法
        ContentValues values = new ContentValues();
        values.put("title", note.title);
        values.put("content", note.content);
        values.put("date", note.date);
        values.put("tag", note.tag);
        db.update("notes", values, "id = ?", new String[]{note.id + ""});
    }

    public void delete(int id) {
        //使用拼接
//        String sql = "delete from notes where id = '"+id+"'";
//        db.execSQL(sql);

        //使用占位符
//        String sql = "delete from notes where id = ?";
//        db.execSQL(sql,new Object[]{id});

        //使用系统方法
        db.delete("notes", "id = ? ", new String[]{id + ""});
    }

    public List<Note> query() {
        List<Note> notes = new ArrayList<>();
        String sql = "select * from Notes";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getColumnIndex("id");
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                Note note = new Note(id, title, content, date, tag);
                notes.add(note);
            }
            cursor.close();
        }
        return notes;
    }
}
