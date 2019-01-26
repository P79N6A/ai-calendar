package org.tikitaka.s_cheduler.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.tikitaka.s_cheduler.R;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {

    private org.tikitaka.s_cheduler.Fragment.BoardFragment.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board,container, false);

        // Setting ViewPager for each Tabs
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.container);
        setupViewPager(mViewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
        return view;
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Tab1Onging());
        adapter.addFragment(new Tab2Finish());
        adapter.addFragment(new Tab3Disable());

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private static final String TAG="SectionsPagerAdapter";

        private final List<Fragment> mFragmentList=new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public  Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1Onging tab1= new Tab1Onging();
                    return tab1;
                case 1:
                    Tab2Finish tab2= new Tab2Finish();
                    return tab2;
                case 2:
                    Tab3Disable tab3= new Tab3Disable();
                    return tab3;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        public void addFragment(Fragment fragment){
            mFragmentList.add(fragment);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

