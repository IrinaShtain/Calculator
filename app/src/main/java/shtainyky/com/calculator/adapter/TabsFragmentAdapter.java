package shtainyky.com.calculator.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import shtainyky.com.calculator.R;
import shtainyky.com.calculator.fragment.Factorial;
import shtainyky.com.calculator.fragment.Pairs;
import shtainyky.com.calculator.fragment.Palindrome;

public class TabsFragmentAdapter extends FragmentPagerAdapter {
    Context context;
    String[] tabs;
    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        tabs = new String[]{context.getString(R.string.tab1_palindrome),
                context.getString(R.string.tab2_factorial),
                context.getString(R.string.tab3_pairs)};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return Palindrome.newInstance();
            case 1:
                return Factorial.newInstance();
            case 2:
                return Pairs.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
