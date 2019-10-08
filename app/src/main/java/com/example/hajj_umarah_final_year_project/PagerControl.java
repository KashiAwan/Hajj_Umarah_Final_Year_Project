package com.example.hajj_umarah_final_year_project;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControl extends FragmentPagerAdapter {
    int tabcounts;

    public PagerControl(FragmentManager fm, int tabcounts) {
        super(fm);
        this.tabcounts = tabcounts;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Homedfltlocation();

            case 1:
                return new Timeofnamaz();

            case 2:
                return new Weatherr();

            case 3:
                return new Qibladirection();

            case 4:
                return new CurrencyConverter();

            case 5:
                return new MinaLocation();

            case 6:
                return new Setingsactiviy();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcounts;
    }
}
