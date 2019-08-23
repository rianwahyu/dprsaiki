package com.business.nation.dprnow.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.business.nation.dprnow.agenda.fragmentHomeAgenda;
import com.business.nation.dprnow.aspirasi.fragmentHomeAspirasi;
import com.business.nation.dprnow.berita.fragmentHomeBerita;

public class TabsHome extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsHome(FragmentManager fm, int NoofTabs) {
        super(fm);

        this.mNumOfTabs=NoofTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                fragmentHomeAspirasi aspirasi = new fragmentHomeAspirasi();
                return aspirasi;
            case 1:
                fragmentHomeBerita berita = new fragmentHomeBerita();
                return berita;
            case 2:
                fragmentHomeAgenda agenda = new fragmentHomeAgenda();
                return agenda;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
