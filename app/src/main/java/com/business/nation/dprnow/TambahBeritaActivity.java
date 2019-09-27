package com.business.nation.dprnow;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.business.nation.dprnow.util.AppController;
import com.business.nation.dprnow.util.DialogHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahBeritaActivity extends AppCompatActivity {

    EditText etJudulBerita, etTempatBerita, etDeskripsiBerita;
    Button btnSubmit;
    Context context = TambahBeritaActivity.this;
    AlertDialog successDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_berita);

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

        etJudulBerita = findViewById(R.id.et_judulBerita);
        etTempatBerita = findViewById(R.id.et_tempatBerita);
        etDeskripsiBerita = findViewById(R.id.et_deskripsiBerita);

        btnSubmit = findViewById(R.id.btnSubmitBerita);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = etJudulBerita.getText().toString();
                String tempat = etTempatBerita.getText().toString();
                String deskripsi = etDeskripsiBerita.getText().toString();

                if (judul.isEmpty()||tempat.isEmpty()||deskripsi.isEmpty()){
                    Toast.makeText(context, "Mohon Melengkapi Form" , Toast.LENGTH_SHORT).show();
                }else{
                    prosesTambah(judul,tempat,deskripsi);
                }
            }
        });
    }

    private void prosesTambah(final String judul, final String tempat, final String deskripsi) {
        String url = "https://dprd.gresikkab.go.id/dprd/auth/input_berita";
        final ProgressDialog loading = ProgressDialog.show(context,"Upload","Please Wait..", false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("true")){
                    loading.dismiss();
                    successDialog = DialogHelper.successDialog(context,"Data Berita Berhasil disimpan", new DialogHelper.OnOk(){
                        @Override
                        public void onOk(AlertDialog alertDialog, View view) {
                            alertDialog.dismiss();
                            finish();
                        }
                    });
                    successDialog.show();
                }else {
                    loading.dismiss();
                    Toast.makeText(context,"gagal", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("judul", judul);
                params.put("tempat", tempat);
                params.put("deskripsi", deskripsi);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "json_obj_req");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
