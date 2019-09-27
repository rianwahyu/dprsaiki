package com.business.nation.dprnow.regulasi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegulasiActivity extends AppCompatActivity {

    ListView listview;
    private List<ModelRegulasi> listNaskah = new ArrayList<ModelRegulasi>();
    AdapterRegulasi adapterRegulasi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulasi);
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

        listNaskah = new ArrayList<ModelRegulasi>();
        listview = findViewById(R.id.listAnggota);
        adapterRegulasi = new AdapterRegulasi(RegulasiActivity.this, listNaskah);
        listview.setAdapter(adapterRegulasi);
        initRegulasi();
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegulasiActivity.this, DetailAnggota.class);
                startActivity(intent);
            }
        });*/

    }

    private void initRegulasi() {
        String url = NetworkState.getUrl()+"get_data_regulasi/";
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

                        ModelRegulasi mb = new ModelRegulasi();
                        mb.setID(ID);
                        mb.setNAMA(NAMA);
                        mb.setISI(ISI);
                        mb.setFILE_PDF(FILE_PDF);
                        listNaskah.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterRegulasi.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegulasiActivity.this, "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(RegulasiActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
