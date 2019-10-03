package com.business.nation.dprnow.aspirasi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.pengaduan.AdapterPengaduan;
import com.business.nation.dprnow.pengaduan.ModelPengaduan;
import com.business.nation.dprnow.util.AppController;
import com.business.nation.dprnow.util.NetworkState;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragmentHomeAspirasi extends Fragment implements ItemClickListener {
    RecyclerView recyclerView;
    private List<ModelAspirasi> listPengaduan = new ArrayList<ModelAspirasi>();
    AdapterAspirasi adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    EditText et_search;
    ImageView imgSearch;
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
        et_search = view.findViewById(R.id.et_search);
        imgSearch = view.findViewById(R.id.imgSearch);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        recyclerView.setVisibility(View.INVISIBLE);
        listPengaduan = new ArrayList<ModelAspirasi>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterAspirasi(getActivity(), listPengaduan);
        recyclerView.setAdapter(adapter);
        initAspirasi();
        adapter.setClickListener(this);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = et_search.getText().toString();

                cariAspirasi(query);
            }
        });
    }

    private void cariAspirasi(String query) {
        callSearchAspirasi(query);
    }

    public void callSearchAspirasi(final String query){
        listPengaduan.clear();
        //listPengaduan = new ArrayList<ModelAspirasi>();
        String url = NetworkState.getUrl()+"get_data_search_aspirasi/" ;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i< jsonArray.length(); i++) {
                                JSONObject asp = jsonArray.getJSONObject(i);

                                String ID = asp.getString("ID");
                                String ID_KATEGORI = asp.getString("ID_KATEGORI");
                                String JUDUL = asp.getString("JUDUL");
                                String TANGGAL = asp.getString("TANGGAL");
                                String ISI = asp.getString("ISI");
                                String JAM = asp.getString("JAM");
                                String USER = asp.getString("USER");
                                String STATUS = asp.getString("STATUS");
                                String LIKE = asp.getString("LIKE");
                                String UNLIKE = asp.getString("UNLIKE");
                                String COMMENT = asp.getString("COMMENT");
                                String FOTO = asp.getString("FOTO");

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
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.toString());
                        Toast.makeText(getActivity(),"Data Tidak ditemukan / Koneksi Tidak Stabil", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("judul",query);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private void initAspirasi() {
        String url = NetworkState.getUrl()+"get_data_aspirasi/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                shimmerFrameLayout.stopShimmer();
                recyclerView.setVisibility(View.VISIBLE);
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
                Toast.makeText(getActivity(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                shimmerFrameLayout.stopShimmer();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View view, int position) {
        ModelAspirasi ma = listPengaduan.get(position);
        String id = ma.getID();
        Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
    }
}
