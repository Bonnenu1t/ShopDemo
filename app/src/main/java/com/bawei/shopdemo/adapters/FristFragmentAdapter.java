package com.bawei.shopdemo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.shopdemo.fragment.IndexFragment;

/**
 * Created by Bonnenu1t丶 on 2017/6/21.
 */

public class FristFragmentAdapter extends FragmentPagerAdapter{

    String [] title = {"上新","纸尿裤","奶粉","洗护喂养","玩具","服饰"};

    public FristFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new IndexFragment().newInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];

    }
}
