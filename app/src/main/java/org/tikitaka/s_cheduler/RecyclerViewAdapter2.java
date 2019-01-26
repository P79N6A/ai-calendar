package org.tikitaka.s_cheduler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder> {

    Context mContext;
    List<ItemProcess> mData;

    public RecyclerViewAdapter2(Context mContext, List<ItemProcess> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v=LayoutInflater.from(mContext).inflate(R.layout.item_process,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //if()
           // holder.tv_result.setText("일정 입력 중입니다");
        //else
            holder.tv_result.setText("일정 입력이 불가합니다");

        holder.tv_date.setText(mData.get(position).getDate());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_result;
        private TextView tv_date;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_result=(TextView)itemView.findViewById(R.id.result);
            tv_date=(TextView)itemView.findViewById(R.id.date);
            img=(ImageView)itemView.findViewById(R.id.image_process);

        }

    }

}
