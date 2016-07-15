package me.sin.sqlite;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.sin.sqlite.data.InsertDecoration;
import me.sin.sqlite.data.NoteAdapter;
import me.sin.sqlite.data.db.NoteDao;
import me.sin.sqlite.data.modle.Note;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NoteDao mNoteDao;
    private List<Note> mNotes = new ArrayList<>();
    private NoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoteDao = new NoteDao(this);

        mAdapter = new NoteAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        if (mRecyclerView.getLayoutManager() == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.addItemDecoration(new InsertDecoration(this));
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateNotesActivity.class));

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mNotes.clear();
        mNotes.addAll(mNoteDao.query());
        mAdapter.setNoteList(mNotes);
    }
}
