package com.qf.a6_1food.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qf.a6_1food.bean.Classify;

import java.util.List;

/**
 * Created by 王松 on 2017/2/27.
 */

public class FgAdapter extends FragmentPagerAdapter {
    private List<Classify> classifies;
    private List<Fragment> fragments;

    public FgAdapter(FragmentManager fm, List<Classify> classifies, List<Fragment> fragments) {
        super(fm);
        this.classifies = classifies;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return classifies.get(position).getName();
    }
}
