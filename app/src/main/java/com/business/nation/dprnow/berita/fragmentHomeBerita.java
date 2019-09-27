package com.business.nation.dprnow.berita;

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
import com.business.nation.dprnow.aspirasi.ModelAspirasi;
import com.business.nation.dprnow.pengaduan.AdapterPengaduan;
import com.business.nation.dprnow.pengaduan.ModelPengaduan;
import com.business.nation.dprnow.util.NetworkState;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fragmentHomeBerita extends Fragment {

    RecyclerView recyclerView;
    private List<ModelBerita> listBerita = new ArrayList<ModelBerita>();
    AdapterBerita adapter;
    ArrayList<HashMap<String,String>> getDatalist;
    ShimmerFrameLayout shimmerFrameLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_berita, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.rcBerita);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        recyclerView.setVisibility(View.INVISIBLE);
        listBerita = new ArrayList<ModelBerita>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterBerita(getActivity(), listBerita);
        recyclerView.setAdapter(adapter);
        initBerita();
    }

    private void initBerita() {
        /*String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_berita/";*/
        String url = NetworkState.getUrl()+"get_data_berita/";
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
                        String ID_KEGIATAN =asp.getString("ID_KEGIATAN");
                        String FILE_FOTO =asp.getString("FILE_FOTO");
                        String JAM =asp.getString("JAM");

                        ModelBerita mb = new ModelBerita();
                        mb.setID(ID);
                        mb.setJUDUL(JUDUL);
                        mb.setTANGGAL(TANGGAL);
                        mb.setTEMPAT(TEMPAT);
                        mb.setDESKRIPSI(DESKRIPSI);
                        mb.setJAM(JAM);
                        mb.setID_KEGIATAN(ID_KEGIATAN);
                        mb.setFILE_FOTO(FILE_FOTO);
                        listBerita.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
                /*Toast.makeText(getActivity(), "Ada", Toast.LENGTH_SHORT).show();*/
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
