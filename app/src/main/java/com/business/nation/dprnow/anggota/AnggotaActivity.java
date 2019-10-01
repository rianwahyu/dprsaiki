package com.business.nation.dprnow.anggota;

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
import com.business.nation.dprnow.aspirasi.ItemClickListener;
import com.business.nation.dprnow.berita.ModelBerita;
import com.business.nation.dprnow.regulasi.RegulasiActivity;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnggotaActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView recyclerView;
    private List<ModelAnggota> listAnggota = new ArrayList<ModelAnggota>();
    AdapterAnggota2 adapterAnggota;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AnggotaActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listAnggota = new ArrayList<ModelAnggota>();
        recyclerView = findViewById(R.id.listAnggota);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterAnggota = new AdapterAnggota2(AnggotaActivity.this, listAnggota);
        recyclerView.setAdapter(adapterAnggota);
        initAnggota();
        adapterAnggota.setClickListener(this);

    }

    private void initAnggota() {
        String url = NetworkState.getUrl()+"get_data_anggota/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String NAMA =asp.getString("NAMA");
                        String TGL_LAHIR =asp.getString("TGL_LAHIR");
                        String TEMPAT_LAHIR =asp.getString("TEMPAT_LAHIR");
                        String ALAMAT =asp.getString("ALAMAT");
                        String EMAIL =asp.getString("EMAIL");
                        String NO_HP =asp.getString("NO_HP");
                        String AGAMA =asp.getString("AGAMA");
                        String PARTAI =asp.getString("PARTAI");
                        String FOTO =asp.getString("FOTO");
                        String POSISI =asp.getString("POSISI");
                        String NIK =asp.getString("NIK");


                        ModelAnggota mb = new ModelAnggota();
                        mb.setID(ID);
                        mb.setNAMA(NAMA);
                        mb.setTGL_LAHIR(TGL_LAHIR);
                        mb.setTEMPAT_LAHIR(TEMPAT_LAHIR);
                        mb.setALAMAT(ALAMAT);
                        mb.setEMAIL(EMAIL);
                        mb.setNO_HP(NO_HP);
                        mb.setAGAMA(AGAMA);
                        mb.setPARTAI(PARTAI);
                        mb.setFOTO(FOTO);
                        mb.setPOSISI(POSISI);
                        mb.setNIK(NIK);
                        listAnggota.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterAnggota.notifyDataSetChanged();
                /*Toast.makeText(getActivity(), "Ada", Toast.LENGTH_SHORT).show();*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AnggotaActivity.this, "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(AnggotaActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view, int position) {
        ModelAnggota ma = listAnggota.get(position);

        Intent intent = new Intent(AnggotaActivity.this, DetailAnggota.class);
        intent.putExtra("id_anggota", ma.getID());
        intent.putExtra("NAMA", ma.getNAMA());
        intent.putExtra("TGL_LAHIR", ma.getTGL_LAHIR());
        intent.putExtra("TEMPAT_LAHIR", ma.getTEMPAT_LAHIR());
        intent.putExtra("ALAMAT", ma.getALAMAT());
        intent.putExtra("EMAIL", ma.getEMAIL());
        intent.putExtra("NO_HP", ma.getNO_HP());
        intent.putExtra("AGAMA", ma.getAGAMA());
        intent.putExtra("PARTAI", ma.getPARTAI());
        intent.putExtra("FOTO", ma.getFOTO());
        intent.putExtra("POSISI", ma.getPOSISI());
        intent.putExtra("NIK", ma.getNIK());
        startActivity(intent);

    }
}
