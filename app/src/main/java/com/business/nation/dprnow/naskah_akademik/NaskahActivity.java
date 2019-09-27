package com.business.nation.dprnow.naskah_akademik;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class NaskahActivity extends AppCompatActivity {

    ListView listview;
    private List<ModelNaskahAkademik> listNaskah = new ArrayList<ModelNaskahAkademik>();
    AdapterNaskah adapterNaskah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naskah);
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

        listNaskah = new ArrayList<ModelNaskahAkademik>();
        listview = findViewById(R.id.listAnggota);
        adapterNaskah = new AdapterNaskah(NaskahActivity.this, listNaskah);
        listview.setAdapter(adapterNaskah);
        initAnggota();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NaskahActivity.this, DetailAnggota.class);
                /*intent.putExtra("id_anggota", listAnggota.get(position).getID());
                intent.putExtra("NAMA", listAnggota.get(position).getNAMA());
                intent.putExtra("TGL_LAHIR", listAnggota.get(position).getTGL_LAHIR());
                intent.putExtra("TEMPAT_LAHIR", listAnggota.get(position).getTEMPAT_LAHIR());
                intent.putExtra("ALAMAT", listAnggota.get(position).getALAMAT());
                intent.putExtra("EMAIL", listAnggota.get(position).getEMAIL());
                intent.putExtra("NO_HP", listAnggota.get(position).getNO_HP());
                intent.putExtra("AGAMA", listAnggota.get(position).getAGAMA());
                intent.putExtra("PARTAI", listAnggota.get(position).getPARTAI());
                intent.putExtra("FOTO", listAnggota.get(position).getFOTO());
                intent.putExtra("POSISI", listAnggota.get(position).getPOSISI());
                intent.putExtra("NIK", listAnggota.get(position).getNIK());*/

                startActivity(intent);
            }
        });

    }

    private void initAnggota() {
        String url = NetworkState.getUrl()+"get_data_naskah_akademik/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String NAMA =asp.getString("NAMA");
                        String ISI =asp.getString("ISI");
                        String FILE_PDF =asp.getString("FILE_PDF");

                        ModelNaskahAkademik mb = new ModelNaskahAkademik();
                        mb.setID(ID);
                        mb.setNAMA(NAMA);
                        mb.setISI(ISI);
                        mb.setFILE_PDF(FILE_PDF);
                        listNaskah.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterNaskah.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NaskahActivity.this, "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(NaskahActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
