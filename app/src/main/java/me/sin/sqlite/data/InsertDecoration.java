package me.sin.sqlite.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.sin.sqlite.R;

/**
 * Created by sin on 2016/7/15.
 */
public class InsertDecoration extends RecyclerView.ItemDecoration {
    private int margin;
    public InsertDecoration(Context context) {
        super();
        margin = (int) context.getResources().getDimension(R.dimen.divide_margin);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,margin);
    }
}
