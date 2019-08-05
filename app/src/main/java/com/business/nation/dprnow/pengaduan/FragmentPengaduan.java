package com.business.nation.dprnow.pengaduan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.nation.dprnow.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentPengaduan extends Fragment {
    RecyclerView recyclerView;
    private List<ModelPengaduan> listPengaduan = new ArrayList<ModelPengaduan>();
    AdapterPengaduan adapter;
    ArrayList<HashMap<String,String>> getDatalist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pengaduan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcPengaduan);
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

        adapter = new AdapterPengaduan(getActivity(), getDatalist, recyclerView);
        recyclerView.setAdapter(adapter);

        /*adapter.setOnItemListener(new AdapterPengaduan.OnItemClickListener() {
            @Override
            public void onItemClick(HashMap<String, String> item) {
                Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
            }
        });*/

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
    }
}
