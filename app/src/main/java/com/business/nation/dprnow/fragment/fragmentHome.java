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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.agenda.fragmentHomeAgenda;
import com.business.nation.dprnow.aspirasi.fragmentHomeAspirasi;
import com.business.nation.dprnow.berita.fragmentHomeBerita;
import com.business.nation.dprnow.pengaduan.FragmentPengaduan;

import java.util.ArrayList;
import java.util.List;

public class fragmentHome extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    EditText et_search;
    ImageView imgSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_search = view.findViewById(R.id.et_search);
        imgSearch = view.findViewById(R.id.imgSearch);


        //setupViewPager(viewPager);


        tabLayout = (TabLayout) view.findViewById(R.id.tabHome);
        tabLayout.addTab(tabLayout.newTab().setText("Aspirasi"));
        tabLayout.addTab(tabLayout.newTab().setText("Berita"));
        tabLayout.addTab(tabLayout.newTab().setText("Agenda"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        TabsHome tabsHome = new TabsHome(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsHome);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = et_search.getText().toString();

                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
            }
        });
        //tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new fragmentHomeAspirasi(), "ASPIRASI");
        adapter.addFragment(new fragmentHomeBerita(), "BERITA");
        adapter.addFragment(new fragmentHomeAgenda(), "AGENDA");
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
