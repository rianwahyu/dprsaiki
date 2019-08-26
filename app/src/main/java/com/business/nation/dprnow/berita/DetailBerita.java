package com.business.nation.dprnow.berita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.util.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailBerita extends AppCompatActivity {

    TextView textTanggal, textJam, textJudul, textIsi, textTempat;
    ImageView imgFoto;
    String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

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

        textTanggal = findViewById(R.id.textTanggalBerita);
        textJudul = findViewById(R.id.textJudulBerita);
        textIsi = findViewById(R.id.textIsiBerita);
        textTempat = findViewById(R.id.textTempatBerita);

        id = getIntent().getStringExtra("id");

        getDetailAspirasi(id);
    }

    public void getDetailAspirasi(final String id){
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_single_berita/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);

                    String ID = jObj.getString("ID");
                    String JUDUL = jObj.getString("JUDUL");
                    String TANGGAL = jObj.getString("TANGGAL");
                    String DESKRIPSI = jObj.getString("DESKRIPSI");
                    String JAM = jObj.getString("JAM");
                    String TEMPAT = jObj.getString("TEMPAT");

                    textJudul.setText(JUDUL);
                    textTempat.setText(TEMPAT);
                    textTanggal.setText(TANGGAL + ", " +JAM);
                    textIsi.setText(DESKRIPSI);
                    /*textLike.setText(LIKE);
                    textComment.setText(COMMENT);*/


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id );
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
