package com.business.nation.dprnow.anggota;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailAnggota extends AppCompatActivity {

    ImageView imgFoto;
    String id_anggota, nama, tgllahir, tempatlahir, alamat, email, nohp, agama, partai, foto, posisi, nik;
    TextView textNamaAnggota, textTempatLahir, textAgama, textEmail, textnoHp;

    RecyclerView listviewPendidikan, listviewPekerjaan, listviewOrganisasi, listviewPenghargaan;
    private List<ModelPendidikanAnggota> listPendidikan = new ArrayList<ModelPendidikanAnggota>();
    private List<ModelPekerjaanAnggota> listPekerjaan = new ArrayList<ModelPekerjaanAnggota>();
    private List<ModelOrganisasiAnggota> listOrganisasi = new ArrayList<ModelOrganisasiAnggota>();
    private List<ModelPenghargaanAnggota> listPenghargaan = new ArrayList<ModelPenghargaanAnggota>();

    AdapterPendidikanAnggota adapterPendidikanAnggota;
    AdapterPekerjaanAnggota adapterPekerjaanAnggota;
    AdapterOrganisasiAnggota adapterOrganisasiAnggota;
    AdapterPenghargaanAnggota adapterPenghargaanAnggota;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anggota);

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

        textNamaAnggota = findViewById(R.id.textNamaAnggota);
        textTempatLahir = findViewById(R.id.textTglLahirAnggota);
        textAgama = findViewById(R.id.textAgamaAnggota);
        textEmail = findViewById(R.id.textEmailAnggota);
        textnoHp = findViewById(R.id.textNoHPAnggota);

        id_anggota = getIntent().getStringExtra("id_anggota");
        nama = getIntent().getStringExtra("NAMA");
        tgllahir = getIntent().getStringExtra("TGL_LAHIR");
        tempatlahir = getIntent().getStringExtra("TEMPAT_LAHIR");
        alamat = getIntent().getStringExtra("ALAMAT");
        email = getIntent().getStringExtra("EMAIL");
        nohp = getIntent().getStringExtra("NO_HP");
        agama = getIntent().getStringExtra("AGAMA");
        partai = getIntent().getStringExtra("PARTAI");
        foto = getIntent().getStringExtra("FOTO");
        posisi = getIntent().getStringExtra("POSISI");
        nik = getIntent().getStringExtra("NIK");

        textNamaAnggota.setText(nama);
        textTempatLahir.setText("Tempat, Tanggal lahir :\n" +tempatlahir+", "+tgllahir );
        textAgama.setText("Agama :\n" +agama);
        textEmail.setText("Email :\n"+ email);
        textnoHp.setText("Nomor Hp :\n" + nohp);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailAnggota.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listviewPendidikan = findViewById(R.id.listPendidikanAnggota);
        listviewPekerjaan = findViewById(R.id.listPekerjaanAnggota);
        listviewOrganisasi = findViewById(R.id.listOrganisasiAnggota);
        listviewPenghargaan = findViewById(R.id.listPenghargaanAnggota);

        listPendidikan = new ArrayList<ModelPendidikanAnggota>();
        listviewPendidikan.setHasFixedSize(true);
        listviewPendidikan.setLayoutManager(linearLayoutManager);
        adapterPendidikanAnggota = new AdapterPendidikanAnggota(DetailAnggota.this, listPendidikan);
        listviewPendidikan.setAdapter(adapterPendidikanAnggota);


        LinearLayoutManager llpek = new LinearLayoutManager(DetailAnggota.this);
        llpek.setOrientation(LinearLayoutManager.VERTICAL);
        listPekerjaan = new ArrayList<ModelPekerjaanAnggota>();
        listviewPekerjaan.setHasFixedSize(true);
        listviewPekerjaan.setLayoutManager(llpek);
        adapterPekerjaanAnggota = new AdapterPekerjaanAnggota(DetailAnggota.this, listPekerjaan);
        listviewPekerjaan.setAdapter(adapterPekerjaanAnggota);

        LinearLayoutManager llorg = new LinearLayoutManager(DetailAnggota.this);
        llorg.setOrientation(LinearLayoutManager.VERTICAL);
        listOrganisasi = new ArrayList<ModelOrganisasiAnggota>();
        listviewOrganisasi.setHasFixedSize(true);
        listviewOrganisasi.setLayoutManager(llorg);
        adapterOrganisasiAnggota = new AdapterOrganisasiAnggota(DetailAnggota.this, listOrganisasi);
        listviewOrganisasi.setAdapter(adapterPekerjaanAnggota);

        LinearLayoutManager llpeng = new LinearLayoutManager(DetailAnggota.this);
        llpeng.setOrientation(LinearLayoutManager.VERTICAL);
        listPenghargaan = new ArrayList<ModelPenghargaanAnggota>();
        listviewPenghargaan.setHasFixedSize(true);
        listviewPenghargaan.setLayoutManager(llpeng);
        adapterPenghargaanAnggota = new AdapterPenghargaanAnggota(DetailAnggota.this, listPenghargaan);
        listviewPenghargaan.setAdapter(adapterPenghargaanAnggota);

        getPenddikan(id_anggota);
        getPekerjaan(id_anggota);
        getOrganisasi(id_anggota);
        getPenghargaan(id_anggota);
    }

    public void getPenddikan(final String id){
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_pendidikan/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonarray=new JSONArray(response);
                    for(int i=0;i<jsonarray.length();i++){
                        JSONObject jObj = jsonarray.getJSONObject(i);
                        String ID = jObj.getString("ID");
                        String ID_ANGGOTA = jObj.getString("ID_ANGGOTA");
                        String NAMA = jObj.getString("NAMA");
                        String TAHUN = jObj.getString("TAHUN");

                        ModelPendidikanAnggota mpa = new ModelPendidikanAnggota(ID,ID_ANGGOTA, NAMA, TAHUN);
                        listPendidikan.add(mpa);
                    }

                    adapterPendidikanAnggota.notifyDataSetChanged();
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
                params.put("id_anggota",id );
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getPekerjaan(final String id){
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_pekerjaan/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonarray=new JSONArray(response);
                    for(int i=0;i<jsonarray.length();i++){
                        JSONObject jObj = jsonarray.getJSONObject(i);
                        String ID = jObj.getString("ID");
                        String ID_ANGGOTA = jObj.getString("ID_ANGGOTA");
                        String NAMA = jObj.getString("NAMA");
                        String TAHUN = jObj.getString("TAHUN");

                        ModelPekerjaanAnggota mpa = new ModelPekerjaanAnggota(ID,ID_ANGGOTA, NAMA, TAHUN);
                        listPekerjaan.add(mpa);
                    }

                    adapterPekerjaanAnggota.notifyDataSetChanged();
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
                params.put("id_anggota",id );
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getOrganisasi(final String id){
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_organisasi/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonarray=new JSONArray(response);
                    for(int i=0;i<jsonarray.length();i++){
                        JSONObject jObj = jsonarray.getJSONObject(i);
                        String ID = jObj.getString("ID");
                        String ID_ANGGOTA = jObj.getString("ID_ANGGOTA");
                        String NAMA = jObj.getString("NAMA");
                        String TAHUN = jObj.getString("TAHUN");

                        ModelOrganisasiAnggota mpa = new ModelOrganisasiAnggota(ID,ID_ANGGOTA, NAMA, TAHUN);
                        listOrganisasi.add(mpa);
                    }

                    adapterOrganisasiAnggota.notifyDataSetChanged();
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
                params.put("id_anggota",id );
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getPenghargaan(final String id){
        String url = "https://dprd.gresikkab.go.id/dprd/auth/get_data_penghargaan/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonarray=new JSONArray(response);
                    for(int i=0;i<jsonarray.length();i++){
                        JSONObject jObj = jsonarray.getJSONObject(i);
                        String ID = jObj.getString("ID");
                        String ID_ANGGOTA = jObj.getString("ID_ANGGOTA");
                        String NAMA = jObj.getString("NAMA");
                        String TAHUN = jObj.getString("TAHUN");

                        ModelPenghargaanAnggota mpa = new ModelPenghargaanAnggota(ID,ID_ANGGOTA, NAMA, TAHUN);
                        listPenghargaan.add(mpa);
                    }

                    adapterPenghargaanAnggota.notifyDataSetChanged();
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
                params.put("id_anggota",id );
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
