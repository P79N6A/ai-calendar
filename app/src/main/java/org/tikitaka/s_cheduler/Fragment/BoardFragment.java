package org.tikitaka.s_cheduler.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.tikitaka.s_cheduler.ItemProcess;
import org.tikitaka.s_cheduler.R;
import org.tikitaka.s_cheduler.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {

    View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_board, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tablelayout_id);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new Tab1Ongoing(), "진행 중");
        adapter.AddFragment(new Tab2Finish(), "완료");
        adapter.AddFragment(new Tab3Disable(), "불가");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);
        return v;
    }

}
