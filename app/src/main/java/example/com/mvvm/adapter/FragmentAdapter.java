package example.com.mvvm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import example.com.mvvm.AboutFragment;
import example.com.mvvm.FoodFragment;
import example.com.mvvm.MapFragment;
import example.com.mvvm.PhotoFragment;
import example.com.mvvm.R;

/**
 * Created by DELL on 2018/2/1.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<String> fragments;

    private List<Fragment> fragmentList;
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        this.fragments= new ArrayList<>();
        this.fragmentList=new ArrayList<>();
        fragments.add("美食");
        fragments.add("地图");
        fragments.add("摄影");
        fragments.add("关于");
        fragmentList.add(new FoodFragment());
        fragmentList.add(new MapFragment());
        fragmentList.add(new PhotoFragment());
        fragmentList.add(new AboutFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position);
    }

}
