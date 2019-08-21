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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.pengaduan.AdapterPengaduan;
import com.business.nation.dprnow.pengaduan.ModelPengaduan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fragmentHomeAspirasi extends Fragment {
    RecyclerView recyclerView;
    private List<ModelAspirasi> listPengaduan = new ArrayList<ModelAspirasi>();
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
        listPengaduan = new ArrayList<ModelAspirasi>();
        /*listPengaduan = new ArrayList<>();

        getDatalist = new ArrayList<>();
        for(int aind = 0 ; aind < 20; aind++){
            HashMap<String,String> map = new HashMap<>();
            map.put("KEY_EMAIL","android" + aind + "@gmail.com");
            map.put("KEY_PHONE","aaa");
            getDatalist.add(map);
            //listPengaduan.add(map);
        }*/

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterAspirasi(getActivity(), listPengaduan);
        recyclerView.setAdapter(adapter);


        initAspirasi();
    }

    private void initAspirasi() {
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_aspirasi/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String ID_KATEGORI =asp.getString("ID_KATEGORI");
                        String JUDUL =asp.getString("JUDUL");
                        String TANGGAL =asp.getString("TANGGAL");
                        String ISI =asp.getString("ISI");
                        String JAM =asp.getString("JAM");
                        String USER =asp.getString("USER");
                        String STATUS =asp.getString("STATUS");
                        String LIKE =asp.getString("LIKE");
                        String UNLIKE =asp.getString("UNLIKE");
                        String COMMENT =asp.getString("COMMENT");
                        String FOTO =asp.getString("FOTO");

                        ModelAspirasi ma = new ModelAspirasi();
                        ma.setID(ID);
                        ma.setIDKATEGORI(ID_KATEGORI);
                        ma.setJUDUL(JUDUL);
                        ma.setTANGGAL(TANGGAL);
                        ma.setISI(ISI);
                        ma.setJAM(JAM);
                        ma.setUSER(USER);
                        ma.setSTATUS(STATUS);
                        ma.setLIKE(LIKE);
                        ma.setUNLIKE(UNLIKE);
                        ma.setCOMMENT(COMMENT);
                        ma.setFOTO(FOTO);
                        listPengaduan.add(ma);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }
}
