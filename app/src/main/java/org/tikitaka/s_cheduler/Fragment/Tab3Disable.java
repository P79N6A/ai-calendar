package org.tikitaka.s_cheduler.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tikitaka.s_cheduler.ItemProcess;
import org.tikitaka.s_cheduler.R;
import org.tikitaka.s_cheduler.Adapter.RecyclerViewAdapter2;

import java.util.ArrayList;
import java.util.List;

public class Tab3Disable extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<ItemProcess> processFirst;

    public Tab3Disable() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab3_disable, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.disable_recyclerview);
        RecyclerViewAdapter2 recyclerAdapter = new RecyclerViewAdapter2(getContext(),processFirst);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        processFirst = new ArrayList<>();
        processFirst.add(new ItemProcess("", "2019년 6월 12일", R.drawable.ic_photoblank));
        processFirst.add(new ItemProcess("", "2019년 5월 20일", R.drawable.ic_photoblank));

    }
}
