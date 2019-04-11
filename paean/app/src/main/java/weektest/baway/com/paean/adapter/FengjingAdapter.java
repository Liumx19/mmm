package weektest.baway.com.paean.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class FengjingAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String[] str;

    public FengjingAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] str) {
        super(fm);
        this.list = list;
        this.str = str;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
