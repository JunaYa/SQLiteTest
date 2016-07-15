package me.sin.sqlite.data.db;

import android.test.AndroidTestCase;

import me.sin.sqlite.data.modle.Note;

import static org.junit.Assert.*;

/**
 * Created by sin on 2016/7/15.
 */
public class NoteDaoTest extends AndroidTestCase{

    public void testInsert(){
        NoteDao noteDao = new NoteDao(getContext());
        Note note = new Note("title","content","2016-07-14","test");
        noteDao.insert(note);

    }
}