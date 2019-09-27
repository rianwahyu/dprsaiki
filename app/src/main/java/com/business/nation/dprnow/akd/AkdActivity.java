package com.business.nation.dprnow.akd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.anggota.AdapterAnggota;
import com.business.nation.dprnow.anggota.DetailAnggota;
import com.business.nation.dprnow.anggota.ModelAnggota;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AkdActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<ModelAkd> listAnggota = new ArrayList<ModelAkd>();
    AdapterAkd adapterAnggota;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akd);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_black);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listAnggota = new ArrayList<ModelAkd>();
        recyclerView = findViewById(R.id.listAkd);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterAnggota = new AdapterAkd(AkdActivity.this, listAnggota);
        recyclerView.setAdapter(adapterAnggota);
        initAKD();

    }

    private void initAKD() {
        String url = NetworkState.getUrl()+"get_data_struktur_akd/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String SEKERTARIS =asp.getString("SEKERTARIS");
                        String UMUM =asp.getString("UMUM");
                        String KEUANGAN =asp.getString("KEUANGAN");
                        String HUMAS =asp.getString("HUMAS");
                        String PERSIDANGAN =asp.getString("PERSIDANGAN");


                        ModelAkd mb = new ModelAkd();
                        mb.setID(ID);
                        mb.setSEKERTARIS(SEKERTARIS);
                        mb.setUMUM(UMUM);
                        mb.setKEUANGAN(KEUANGAN);
                        mb.setHUMAS(HUMAS);
                        mb.setPERSIDANGAN(PERSIDANGAN);
                        listAnggota.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterAnggota.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AkdActivity.this, "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(AkdActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
