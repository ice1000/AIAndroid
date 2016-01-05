package util;

import android.view.MotionEvent;
import android.view.View;

/**
 * Copyright 2015(c) Comet Corporation.
 * Created by asus1 on 2015/12/31.
 */

public interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view, int position);
    void onItemTouch(View view, int position, MotionEvent event);
}
