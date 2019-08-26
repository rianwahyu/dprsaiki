package com.business.nation.dprnow.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.business.nation.dprnow.aspirasi.ModelAspirasi;
import com.business.nation.dprnow.streaming.AdapterStreaming;
import com.business.nation.dprnow.streaming.ModelStreaming;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentStreaming extends Fragment {

    RecyclerView recyclerView;
    AdapterStreaming adapterStreaming;
    private List<ModelStreaming> listStreaming = new ArrayList<ModelStreaming>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listStreaming = new ArrayList<ModelStreaming>();
        recyclerView = view.findViewById(R.id.rcStreaming);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        adapterStreaming = new AdapterStreaming(getActivity(), listStreaming);
        recyclerView.setAdapter(adapterStreaming);
        initStreaming();
    }

    private void initStreaming() {
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_vidio_youtube/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String NAMA =asp.getString("NAMA");
                        String URL =asp.getString("URL");

                        ModelStreaming ms = new ModelStreaming(ID, NAMA, URL);
                        listStreaming.add(ms);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterStreaming.notifyDataSetChanged();

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
