package org.tikitaka.s_cheduler.Helper;

import android.support.v7.widget.RecyclerView;

interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder,int direction,int position);
}
