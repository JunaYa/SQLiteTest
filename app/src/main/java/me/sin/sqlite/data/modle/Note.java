package me.sin.sqlite.data.modle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sin on 2016/7/15.
 */
public class Note implements Parcelable{
    public Integer id;
    public String title;
    public String content;
    public String date;
    public String tag;
    public User user;

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


    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        date = in.readString();
        tag = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(date);
        parcel.writeString(tag);
    }
}
