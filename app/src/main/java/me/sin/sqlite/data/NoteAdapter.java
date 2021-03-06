package me.sin.sqlite.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.sin.sqlite.R;
import me.sin.sqlite.data.modle.Note;

/**
 * Created by sin on 2016/7/15.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> mNoteList;
    private NoteClick mNoteClick;

    public void setNoteClick(NoteClick noteClick) {
        mNoteClick = noteClick;
    }

    public void setNoteList(List<Note> noteList) {
        mNoteList = noteList;
        notifyDataSetChanged();
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteHolder holder, final int position) {
        final Note note = mNoteList.get(position);

        holder.title.setText(note.title);
        holder.date.setText(note.date);
        holder.content.setText(note.content);

        if (mNoteClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mNoteClick.onClick(position, note);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mNoteClick.onLongClick(position,note);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNoteList == null ? 0 : mNoteList.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;
        private TextView content;

        public NoteHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    public interface NoteClick {
        void onClick(int pos, Note note);

        void onLongClick(int pos, Note note);
    }
}
