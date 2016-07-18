package me.sin.sqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.sin.sqlite.data.db.NoteDao;
import me.sin.sqlite.data.modle.Note;

public class CreateNotesActivity extends AppCompatActivity implements View.OnClickListener {


    private NoteDao mNoteDao;
    private CoordinatorLayout mLayout;
    private FlexboxLayout mFlexboxLayout;
    private EditText mTitle, mContent, mTag;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Note mNote;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreateNotesActivity.class);
        return intent;
    }

    public static Intent newIntent(Context context, Note note) {
        Intent intent = new Intent(context, CreateNotesActivity.class);
        intent.putExtra(MainActivity.ARG_NOTE, note);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        mNote = getIntent().getParcelableExtra(MainActivity.ARG_NOTE);
        initView();
        setViewData();
    }

    private void initView() {
        mNoteDao = new NoteDao(this);
        mLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        initFlexBox();
        mTitle = (EditText) findViewById(R.id.title);
        mContent = (EditText) findViewById(R.id.content);
        mTag = (EditText) findViewById(R.id.tag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViewById(R.id.fab).setOnClickListener(this);
    }

    private void initFlexBox() {
        mFlexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox);
        mFlexboxLayout.removeAllViews();
        View view = LayoutInflater.from(this).inflate(R.layout.item_layout_flexbox, null);
        TextView textView = ((TextView) view.findViewById(R.id.tag_name));
        textView.setText("add");

        mFlexboxLayout.addView(view);

//        for (int i = 0; i < 5; i++) {
//            int tagCount = mFlexboxLayout.getChildCount();
//            textView.setText("add" + i);
//            mFlexboxLayout.addView(view);
//        }
    }

    private FlexboxLayout.LayoutParams createDefaultLayoutParams() {
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.order = 1;
        lp.flexGrow = 2;
        lp.flexShrink = 2;
        int flexBasisPercent = 2;
        lp.flexBasisPercent = flexBasisPercent == -1 ? -1 : (float) (flexBasisPercent / 100.0);
        return lp;
    }

    private void setViewData() {
        if (mNote != null) {
            mTitle.setText(mNote.title);
            mContent.setText(mNote.content);
            mTag.setText(mNote.tag);
        }
    }

    private String format(Date date) {
        return mDateFormat.format(date);
    }

    private String nowDate() {
        return format(new Date());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                saveNote();
                break;
        }

    }

    private void saveNote() {
        if (!TextUtils.isEmpty(mTitle.getText().toString()))
            if (!TextUtils.isEmpty(mContent.getText().toString()))
                if (!TextUtils.isEmpty(mTag.getText().toString())) {
                    if (mNote != null) {
                        Note note = new Note(mNote.id, mTitle.getText().toString(), mContent.getText().toString(), nowDate(), mTag.getText().toString());
                        boolean isOk = mNoteDao.update(note);
                        if (isOk) {
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Snackbar.make(mLayout, "note create error", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Note note = new Note(mTitle.getText().toString(), mContent.getText().toString(), nowDate(), mTag.getText().toString());
                        boolean isOk = mNoteDao.insert(note);
                        if (isOk) {
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Snackbar.make(mLayout, "note create error", Snackbar.LENGTH_SHORT).show();
                        }
                    }


                }
    }
}
