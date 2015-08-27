package com.gdgssu.android_deviewsched.ui.sche;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gdgssu.android_deviewsched.model.AllScheItems;

import java.util.ArrayList;

public class SchePagerFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<CharSequence> pagerTitles = new ArrayList<CharSequence>();

    public SchePagerFragmentAdapter(FragmentManager fm) {
        super(fm);

        setPagerTitles();
    }

    private void setPagerTitles() {
        pagerTitles.add("Track1");
        pagerTitles.add("Track2");
        pagerTitles.add("Track3");
        pagerTitles.add("Track4");

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return SchePagerFragment.newInstance(AllScheItems.result.days.get(0).tracks.get(position));
            case 1:
                return SchePagerFragment.newInstance(AllScheItems.result.days.get(0).tracks.get(position));
            case 2:
                return SchePagerFragment.newInstance(AllScheItems.result.days.get(0).tracks.get(position));
            case 3:
                return SchePagerFragment.newInstance(AllScheItems.result.days.get(0).tracks.get(position));
            default:
                throw new RuntimeException("There is not case");
        }
    }

    @Override
    public int getCount() {
        return pagerTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitles.get(position);
    }
}
