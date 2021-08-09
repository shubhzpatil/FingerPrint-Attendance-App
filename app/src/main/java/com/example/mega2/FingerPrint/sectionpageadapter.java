package com.example.mega2.FingerPrint;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class sectionpageadapter extends FragmentPagerAdapter {

    private final List<Fragment> mfragmentList=new ArrayList<>();
    private final List<String> mTitlefragmentList=new ArrayList<>();

    void addfragment(Fragment fragment, String title)
    {
        mfragmentList.add(fragment);
        mTitlefragmentList.add(title);
    }

    sectionpageadapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
       return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return mTitlefragmentList.get(position);
    }
}
