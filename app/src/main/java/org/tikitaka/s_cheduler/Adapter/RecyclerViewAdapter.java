package org.tikitaka.s_cheduler.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.tikitaka.s_cheduler.ItemSuccess;
import org.tikitaka.s_cheduler.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<ItemSuccess> mData;

    public RecyclerViewAdapter(Context mContext, List<ItemSuccess> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.item_success,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_date.setText(mData.get(position).getDate());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private TextView tv_date;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_title=(TextView)itemView.findViewById(R.id.title_event);
            tv_date=(TextView)itemView.findViewById(R.id.date_event);
            img=(ImageView)itemView.findViewById(R.id.image_success);

        }

    }

}
