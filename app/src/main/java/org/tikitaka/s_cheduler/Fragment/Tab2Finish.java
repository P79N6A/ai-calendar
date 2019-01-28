package org.tikitaka.s_cheduler.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tikitaka.s_cheduler.ItemSuccess;
import org.tikitaka.s_cheduler.R;
import org.tikitaka.s_cheduler.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Tab2Finish extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<ItemSuccess> successFirst;
    public Tab2Finish(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab2_finish, container, false);
        myrecyclerview=(RecyclerView)v.findViewById(R.id.finish_recyclerview);
        RecyclerViewAdapter recyclerAdapter=new RecyclerViewAdapter(getContext(),successFirst);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        successFirst=new ArrayList<>();
        successFirst.add(new ItemSuccess("영상 공모전","2019년 6월 12일",R.drawable.ic_photoblank));
        successFirst.add(new ItemSuccess("글짓기 공모전","2019년 5월 20일",R.drawable.ic_photoblank));
        successFirst.add(new ItemSuccess("영상 공모전","2019년 6월 12일",R.drawable.ic_photoblank));
        successFirst.add(new ItemSuccess("영상 공모전","2019년 6월 12일",R.drawable.ic_photoblank));


    }
}

