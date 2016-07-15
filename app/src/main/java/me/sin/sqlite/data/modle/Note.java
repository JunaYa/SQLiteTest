package me.sin.sqlite.data.modle;

/**
 * Created by sin on 2016/7/15.
 */
public class Note {
    public int id;
    public String title;
    public String content;
    public String date;
    public String tag;

    public Note() {
    }

    public Note(String title, String content, String date, String tag) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.tag = tag;
    }

    public Note(int id, String title, String content, String date, String tag) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.tag = tag;
    }
}
