package org.tikitaka.s_cheduler.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> FirstFragment=new ArrayList<>();
    private final List<String> FirstTitle=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FirstFragment.get(position);
    }

    @Override
    public int getCount() {
        return FirstTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FirstTitle.get(position);
    }

    public void AddFragment (Fragment fragment, String title){
        FirstFragment.add(fragment);
        FirstTitle.add(title);
    }
}
