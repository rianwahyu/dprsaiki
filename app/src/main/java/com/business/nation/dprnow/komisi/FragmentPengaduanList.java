package com.business.nation.dprnow.komisi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.komisi.AdapterPengaduanList;
import com.business.nation.dprnow.komisi.FragmentBadanMusyawarah;
import com.business.nation.dprnow.komisi.FragmentKomisi1;
import com.business.nation.dprnow.komisi.FragmentKomisi2;
import com.business.nation.dprnow.komisi.FragmentKomisi3;
import com.business.nation.dprnow.komisi.FragmentKomisi4;
import com.business.nation.dprnow.komisi.FragmentKomisi5;
import com.business.nation.dprnow.komisi.FragmentKomisi6;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentPengaduanList extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    RecyclerView recyclerView;
    AdapterPengaduanList apl;
    private List<ModelPengaduanList> lisrPengaduan = new ArrayList<ModelPengaduanList>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_komisi, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rcPengaduanList);
        lisrPengaduan = new ArrayList<ModelPengaduanList>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        apl = new AdapterPengaduanList(getActivity(), lisrPengaduan);
        recyclerView.setAdapter(apl);
        /*viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabKomisi);
        tabLayout.setupWithViewPager(viewPager);*/
        initPengaduan();
    }

    private void initPengaduan() {
        /*String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_berita/";*/
        String url = NetworkState.getUrl()+"get_data_kategori_pengaduan/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                /*shimmerFrameLayout.setVisibility(View.INVISIBLE);
                shimmerFrameLayout.stopShimmer();*/
                recyclerView.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String NAMA =asp.getString("NAMA");

                        ModelPengaduanList mb = new ModelPengaduanList();
                        mb.setID(ID);
                        mb.setNAMA(NAMA);
                        lisrPengaduan.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                apl.notifyDataSetChanged();
                /*Toast.makeText(getActivity(), "Ada", Toast.LENGTH_SHORT).show();*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Eror", Toast.LENGTH_SHORT).show();
                /*shimmerFrameLayout.setVisibility(View.INVISIBLE);
                shimmerFrameLayout.stopShimmer();*/
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
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
