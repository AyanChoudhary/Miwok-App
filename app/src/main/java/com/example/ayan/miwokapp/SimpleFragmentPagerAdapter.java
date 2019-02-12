package com.example.ayan.miwokapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        if (position == 0)
            return new NumberFragment();
        else if (position == 1)
            return new FamilyFragment();
        else if (position == 2)
            return new ColorFragment();
        else
            return new PhraseFragment();
    }

    @Override
    public int getCount(){
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0)
            return  "Numbers";
        else if (position == 1)
            return "Family";
        else if (position == 2)
            return "Color";
        else
            return "Phrases";
    }
}
