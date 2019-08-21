package com.business.nation.dprnow.agenda;

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
import com.business.nation.dprnow.aspirasi.AdapterAspirasi;
import com.business.nation.dprnow.aspirasi.ModelAspirasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fragmentHomeAgenda extends Fragment {
    RecyclerView recyclerView;
    private List<ModelAgenda> listAgenda = new ArrayList<ModelAgenda>();
    AdapterAgenda adapter;
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
        listAgenda = new ArrayList<ModelAgenda>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterAgenda(getActivity(), listAgenda);
        recyclerView.setAdapter(adapter);
        initAgenda();
    }

    private void initAgenda() {
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_agenda/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String JUDUL =asp.getString("JUDUL");
                        String TANGGAL =asp.getString("TANGGAL");
                        String TEMPAT =asp.getString("TEMPAT");
                        String DESKRIPSI =asp.getString("DESKRIPSI");
                        String JAM =asp.getString("JAM");

                        ModelAgenda ma = new ModelAgenda();
                        ma.setID(ID);
                        ma.setJUDUL(JUDUL);
                        ma.setTANGGAL(TANGGAL);
                        ma.setTEMPAT(TEMPAT);
                        ma.setDESKRIPSI(DESKRIPSI);
                        ma.setJAM(JAM);

                        listAgenda.add(ma);
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
