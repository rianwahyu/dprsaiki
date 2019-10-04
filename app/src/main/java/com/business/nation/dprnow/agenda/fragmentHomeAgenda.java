package com.business.nation.dprnow.agenda;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import com.business.nation.dprnow.aspirasi.AdapterAspirasi;
import com.business.nation.dprnow.aspirasi.ModelAspirasi;
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

public class fragmentHomeAgenda extends Fragment {
    RecyclerView recyclerView;
    private List<ModelAgenda> listAgenda = new ArrayList<ModelAgenda>();
    AdapterAgenda adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    EditText et_search;
    ImageView imgSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_agenda, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcAgenda);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        et_search = view.findViewById(R.id.et_search);
        imgSearch = view.findViewById(R.id.imgSearch);
        recyclerView.setVisibility(View.INVISIBLE);
        listAgenda = new ArrayList<ModelAgenda>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterAgenda(getActivity(), listAgenda);
        recyclerView.setAdapter(adapter);
        initAgenda();

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int act, KeyEvent keyEvent) {
                if ((keyEvent !=null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                        act == EditorInfo.IME_ACTION_DONE){
                    imgSearch.performClick();
                }
                return false;
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_search.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                String query = et_search.getText().toString();

                if (query.isEmpty()){
                    Toast.makeText(getActivity(), "Form Tidak Boleh Kosong ", Toast.LENGTH_SHORT).show();
                }else{
                    cariAgenda(query);
                }


            }
        });
    }

    public void cariAgenda(final String query){
        listAgenda.clear();
        //listPengaduan = new ArrayList<ModelAspirasi>();
        String url = NetworkState.getUrl()+"get_data_search_agenda/" ;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i< jsonArray.length(); i++) {
                                JSONObject asp = jsonArray.getJSONObject(i);

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

    private void initAgenda() {
        /*String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_agenda/";*/
        String url = NetworkState.getUrl()+ "get_data_agenda/";
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
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                shimmerFrameLayout.stopShimmer();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }
}
