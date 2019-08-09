package com.business.nation.dprnow.aspirasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.pengaduan.AdapterPengaduan;
import com.business.nation.dprnow.pengaduan.ModelPengaduan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fragmentHomeAspirasi extends Fragment {
    RecyclerView recyclerView;
    private List<ModelPengaduan> listPengaduan = new ArrayList<ModelPengaduan>();
    AdapterAspirasi adapter;
    ArrayList<HashMap<String,String>> getDatalist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_aspirasi, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcAspirasi);
        listPengaduan = new ArrayList<>();

        getDatalist = new ArrayList<>();
        for(int aind = 0 ; aind < 20; aind++){
            HashMap<String,String> map = new HashMap<>();
            map.put("KEY_EMAIL","android" + aind + "@gmail.com");
            map.put("KEY_PHONE","aaa");
            getDatalist.add(map);
            //listPengaduan.add(map);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new AdapterAspirasi(getActivity(), getDatalist);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
