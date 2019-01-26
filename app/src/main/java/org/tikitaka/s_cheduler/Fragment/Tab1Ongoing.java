package org.tikitaka.s_cheduler.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.tikitaka.s_cheduler.ItemProcess;
import org.tikitaka.s_cheduler.R;
import org.tikitaka.s_cheduler.RecyclerViewAdapter;
import org.tikitaka.s_cheduler.RecyclerViewAdapter2;

import java.util.ArrayList;
import java.util.List;

public class Tab1Ongoing extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<ItemProcess> processFirst;

    public Tab1Ongoing() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab1_ongoing, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.ongoing_recyclerview);
        RecyclerViewAdapter2 recyclerAdapter = new RecyclerViewAdapter2(getContext(),processFirst);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        processFirst = new ArrayList<>();
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 6월 12일", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 5월 20일", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 1", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 2", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 3", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 4", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 5", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("일정 입력 중입니다", "2019년 6", R.drawable.ic_photoblank));

    }
}
