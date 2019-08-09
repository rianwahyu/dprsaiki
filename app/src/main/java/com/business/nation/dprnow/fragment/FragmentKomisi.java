package com.business.nation.dprnow.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.komisi.FragmentBadanMusyawarah;
import com.business.nation.dprnow.komisi.FragmentKomisi1;
import com.business.nation.dprnow.komisi.FragmentKomisi2;
import com.business.nation.dprnow.komisi.FragmentKomisi3;
import com.business.nation.dprnow.komisi.FragmentKomisi4;
import com.business.nation.dprnow.komisi.FragmentKomisi5;
import com.business.nation.dprnow.komisi.FragmentKomisi6;

import java.util.ArrayList;
import java.util.List;

public class FragmentKomisi extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_komisi, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabKomisi);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new FragmentBadanMusyawarah(), "Badan Musyawarah");
        adapter.addFragment(new FragmentKomisi1(), "Komisi 1");
        adapter.addFragment(new FragmentKomisi2(), "Komisi 2");
        adapter.addFragment(new FragmentKomisi3(), "Komisi 3");
        adapter.addFragment(new FragmentKomisi4(), "Komisi 4");
        adapter.addFragment(new FragmentKomisi5(), "Komisi 5");
        adapter.addFragment(new FragmentKomisi6(), "Komisi 6");
        viewPager.setAdapter(adapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
