package me.sin.sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import me.sin.sqlite.data.InsertDecoration;
import me.sin.sqlite.data.NoteAdapter;
import me.sin.sqlite.data.db.NoteDao;
import me.sin.sqlite.data.modle.Note;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ARG_NOTE = "note";
    public static final int REQUEST_FOR_NOTE = 1;

    private CheckBox mRbtLayoutStyle;
    private RecyclerView mRecyclerView;
    private NoteDao mNoteDao;
    private List<Note> mNotes = new ArrayList<>();
    private NoteAdapter mAdapter;

    private LinearLayoutManager mLinerManager;
    private StaggeredGridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteDao = new NoteDao(this);

        mLinerManager = new LinearLayoutManager(MainActivity.this);
        mGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new NoteAdapter();
        mAdapter.setNoteClick(mNoteClick);
        mRbtLayoutStyle = (CheckBox) findViewById(R.id.layout_style);
        mRbtLayoutStyle.setVisibility(View.VISIBLE);
        mRbtLayoutStyle.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        if (mRecyclerView.getLayoutManager() == null) {
            mRecyclerView.setLayoutManager(mLinerManager);
            mRecyclerView.addItemDecoration(new InsertDecoration(this));
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);


        findViewById(R.id.fab).setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mNotes.clear();
        mNotes.addAll(mNoteDao.query());
        mAdapter.setNoteList(mNotes);
    }

    private NoteAdapter.NoteClick mNoteClick = new NoteAdapter.NoteClick() {
        @Override
        public void onClick(int pos, Note note) {
            startActivityForResult(CreateNotesActivity.newIntent(MainActivity.this, note), REQUEST_FOR_NOTE);
        }

        @Override
        public void onLongClick(final int pos, final Note note) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("是否确定删除？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean isDelete = mNoteDao.delete(note.id);
                    if (isDelete) {
                        mNotes.remove(pos);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyItemRemoved(pos);
                        Snackbar.make(mRecyclerView, "delete success", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }).show();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_style:
                //true样式为falls, false样式为liner
                if (mRbtLayoutStyle.isChecked()) {
                    mRecyclerView.setLayoutManager(mGridLayoutManager);
                } else {
                    mRecyclerView.setLayoutManager(mLinerManager);
                }
                break;
            case R.id.fab:
                startActivity(CreateNotesActivity.newIntent(MainActivity.this));
                break;
        }
    }
}
