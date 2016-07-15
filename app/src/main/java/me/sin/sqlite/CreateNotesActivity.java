package me.sin.sqlite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.sin.sqlite.data.db.NoteDao;
import me.sin.sqlite.data.modle.Note;

public class CreateNotesActivity extends AppCompatActivity {

    private NoteDao mNoteDao;
    private EditText mTitle, mContent, mTag;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mNoteDao = new NoteDao(this);
        mTitle = (EditText) findViewById(R.id.title);
        mContent = (EditText) findViewById(R.id.content);
        mTag = (EditText) findViewById(R.id.tag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (!TextUtils.isEmpty(mTitle.getText().toString()))
                    if (!TextUtils.isEmpty(mContent.getText().toString()))
                        if (!TextUtils.isEmpty(mTag.getText().toString())) {
                            Note note = new Note(mTitle.getText().toString(), mContent.getText().toString(), nowDate(), mTag.getText().toString());
                            mNoteDao.insert(note);
                        }

            }
        });
    }

    private String format(Date date) {
        return mDateFormat.format(date);
    }

    private String nowDate() {
        return format(new Date());
    }
}
