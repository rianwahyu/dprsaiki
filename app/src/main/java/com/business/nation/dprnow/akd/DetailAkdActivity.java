package com.business.nation.dprnow.akd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.util.AppController;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailAkdActivity extends AppCompatActivity {

    TextView textNamaSekretaris, textNipSekretaris, textTingkatSekretaris, textUmum, textNipUmum,
    textTingkatUmum, textKeuangan, textNipKeuangan, textTingkatKeuangan, textHumas, textNipHumas,
    textTingkatHumas, textPersidangan, textNipPersidangan, textTingkatPersidangan,
    textTU, textNipTU, textTingkatTU, textProgram, textNipProgram, textTingkatProgram,
    textKehumasan, textNipKehumasan, textTingkatKehumasan, textRisalah, textNipRislalah, textTingkatRislaah,
    textPerlengkapan, textNipPerlengkapan, textTingkatPerlengkapan, textPerpustakaan,
    textNipPerpustakaan, textTingkatPerpustakaan, textUndang, textNipUndang, textTingkatUndang,
    textKelengkapan, textNipKelengkapan, textTingkatKelengkapan, textBendahara, textNipBendahara,
    textTingkatBendahara;

    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akd);
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
        textNamaSekretaris = findViewById(R.id.textNamaSekretaris);
        textNipSekretaris = findViewById(R.id.textNIPSekretaris);
        textTingkatSekretaris = findViewById(R.id.textTingkatSekretaris);
        textUmum = findViewById(R.id.textNamaUmum);
        textNipUmum = findViewById(R.id.textNIPUmum);
        textTingkatUmum = findViewById(R.id.textTingkatUmum);
        textKeuangan = findViewById(R.id.textNamaKeuangan);
        textNipKeuangan = findViewById(R.id.textNIPKeuangan);
        textTingkatKeuangan = findViewById(R.id.textTingkatKeuangan);
        textHumas = findViewById(R.id.textNamaHumas);
        textNipHumas = findViewById(R.id.textNIPHumas);
        textTingkatHumas = findViewById(R.id.textTingkatHumas);
        textPersidangan = findViewById(R.id.textNamaPersidangan);
        textNipPersidangan = findViewById(R.id.textNIPPersidangan);
        textTingkatPersidangan = findViewById(R.id.textTingkatPersidangan);

        textTU = findViewById(R.id.textNamaTU);
        textNipTU = findViewById(R.id.textNIPTU);
        textTingkatTU = findViewById(R.id.textTingkatTU);

        textProgram = findViewById(R.id.textNamaProgram);
        textNipProgram = findViewById(R.id.textNIPProgram);
        textTingkatProgram = findViewById(R.id.textTingkatProgram);

        textKehumasan = findViewById(R.id.textNamaKehumasan);
        textNipKehumasan = findViewById(R.id.textNIPKehumasan);
        textTingkatKehumasan = findViewById(R.id.textTingkatKehumasan);

        textRisalah = findViewById(R.id.textNamaRisalah);
        textNipRislalah = findViewById(R.id.textNIPRisalah);
        textTingkatRislaah = findViewById(R.id.textTingkatRisalah);

        textPerlengkapan = findViewById(R.id.textNamaPerlengkapan);
        textNipPerlengkapan = findViewById(R.id.textNIPPerlengkapan);
        textTingkatPerlengkapan = findViewById(R.id.textTingkatPerlengkapan);

        textPerpustakaan = findViewById(R.id.textNamaPerpustakaan);
        textNipPerpustakaan = findViewById(R.id.textNIPPerpustakaan);
        textTingkatPerpustakaan = findViewById(R.id.textTingkatPerpustakaan);

        textUndang = findViewById(R.id.textNamaUndang);
        textNipUndang = findViewById(R.id.textNIPUndang);
        textTingkatUndang = findViewById(R.id.textTingkatUndang);

        textKelengkapan = findViewById(R.id.textNamaKelengkapan);
        textNipKelengkapan = findViewById(R.id.textNIPKelengkapan);
        textTingkatKelengkapan = findViewById(R.id.textTingkatKelengkapan);

        textBendahara = findViewById(R.id.textNamaBendahara);
        textNipBendahara = findViewById(R.id.textNIPBendahara);
        textTingkatBendahara = findViewById(R.id.textTingkatBendahara);


        id = getIntent().getStringExtra("id");
        getDetailAKD(id);

    }

    public void getDetailAKD(final String id){
        String url = NetworkState.getUrl()+"get_data_single_struktur_akd/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);

                    String ID = jObj.getString("ID");
                    String SEKERTARIS = jObj.getString("SEKERTARIS");
                    String NIP_SEKERTARIS = jObj.getString("NIP_SEKERTARIS");
                    String TINGKAT_SEKERTARIS = jObj.getString("TINGKAT_SEKERTARIS");
                    String UMUM = jObj.getString("UMUM");
                    String NIP_UMUM = jObj.getString("NIP_UMUM");
                    String TINGKAT_UMUM = jObj.getString("TINGKAT_UMUM");
                    String KEUANGAN = jObj.getString("KEUANGAN");
                    String NIP_KEUANGAN = jObj.getString("NIP_KEUANGAN");
                    String TINGKAT_KEUANGAN = jObj.getString("TINGKAT_KEUANGAN");
                    String HUMAS = jObj.getString("HUMAS");
                    String NIP_HUMAS = jObj.getString("NIP_HUMAS");
                    String TINGKAT_HUMAS = jObj.getString("TINGKAT_HUMAS");
                    String PERSIDANGAN = jObj.getString("PERSIDANGAN");
                    String NIP_PERSIDANGAN = jObj.getString("NIP_PERSIDANGAN");
                    String TINGKAT_PERSIDANGAN = jObj.getString("TINGKAT_PERSIDANGAN");
                    String TU = jObj.getString("TU");
                    String NIP_TU = jObj.getString("NIP_TU");
                    String TINGKAT_TU = jObj.getString("TINGKAT_TU");
                    String PROGRAM = jObj.getString("PROGRAM");
                    String NIP_PROGRAM = jObj.getString("NIP_PROGRAM");
                    String TINGKAT_PROGRAM = jObj.getString("TINGKAT_PROGRAM");
                    String KEHUMASAN = jObj.getString("KEHUMASAN");
                    String NIP_KEHUMASAN = jObj.getString("NIP_KEHUMASAN");
                    String TINGKAT_KEHUMASAN = jObj.getString("TINGKAT_KEHUMASAN");
                    String RISALAH = jObj.getString("RISALAH");
                    String NIP_RISALAH = jObj.getString("NIP_RISALAH");
                    String TINGKAT_RISALAH = jObj.getString("TINGKAT_RISALAH");
                    String PERLENGKAPAN = jObj.getString("PERLENGKAPAN");
                    String NIP_PERLENGKAPAN = jObj.getString("NIP_PERLENGKAPAN");
                    String TINGKAT_PERLENGKAPAN = jObj.getString("TINGKAT_PERLENGKAPAN");
                    String PERPUSTAKAAN = jObj.getString("PERPUSTAKAAN");
                    String NIP_PERPUSTAKAAN = jObj.getString("NIP_PERPUSTAKAAN");
                    String TINGKAT_PERPUSTAKAAN = jObj.getString("TINGKAT_PERPUSTAKAAN");
                    String UNDANG = jObj.getString("UNDANG");
                    String NIP_UNDANG = jObj.getString("NIP_UNDANG");
                    String TINGKAT_UNDANG = jObj.getString("TINGKAT_UNDANG");
                    String KELENGKAPAN = jObj.getString("KELENGKAPAN");
                    String NIP_KELENGKAPAN = jObj.getString("NIP_KELENGKAPAN");
                    String TINGKAT_KELENGKAPAN = jObj.getString("TINGKAT_KELENGKAPAN");
                    String BENDAHARA = jObj.getString("BENDAHARA");
                    String NIP_BENDAHARA = jObj.getString("NIP_BENDAHARA");
                    String TINGKAT_BENDAHARA = jObj.getString("TINGKAT_BENDAHARA");

                    textNamaSekretaris.setText(SEKERTARIS);
                    textNipSekretaris.setText(NIP_SEKERTARIS);
                    textTingkatSekretaris.setText(TINGKAT_SEKERTARIS);
                    textUmum.setText(UMUM);
                    textNipUmum.setText(NIP_UMUM);
                    textTingkatUmum.setText(TINGKAT_UMUM);
                    textKeuangan.setText(KEUANGAN);
                    textNipKeuangan.setText(NIP_KEUANGAN);
                    textTingkatKeuangan.setText(TINGKAT_KEUANGAN);
                    textHumas.setText(HUMAS);
                    textNipHumas.setText(NIP_HUMAS);
                    textTingkatHumas.setText(TINGKAT_HUMAS);
                    textPersidangan.setText(PERSIDANGAN);
                    textNipPersidangan.setText(NIP_PERSIDANGAN);
                    textTingkatPersidangan.setText(TINGKAT_PERSIDANGAN);
                    textTU.setText(TU);
                    textNipTU.setText(NIP_TU);
                    textTingkatTU.setText(TINGKAT_TU);
                    textProgram.setText(PROGRAM);
                    textNipProgram.setText(NIP_PROGRAM);
                    textTingkatProgram.setText(TINGKAT_PROGRAM);
                    textKehumasan.setText(KEHUMASAN);
                    textNipKehumasan.setText(NIP_KEHUMASAN);
                    textTingkatKehumasan.setText(TINGKAT_KEHUMASAN);
                    textRisalah.setText(RISALAH);
                    textNipRislalah.setText(NIP_RISALAH);
                    textTingkatRislaah.setText(TINGKAT_RISALAH);
                    textPerlengkapan.setText(PERLENGKAPAN);
                    textNipPerlengkapan.setText(NIP_PERLENGKAPAN);
                    textTingkatPerlengkapan.setText(TINGKAT_PERLENGKAPAN);

                    textPerpustakaan.setText(PERPUSTAKAAN);
                    textNipPerpustakaan.setText(NIP_PERPUSTAKAAN);
                    textTingkatPerpustakaan.setText(TINGKAT_PERPUSTAKAAN);

                    textPerlengkapan.setText(UNDANG);
                    textNipPerlengkapan.setText(NIP_UNDANG);
                    textTingkatPerlengkapan.setText(TINGKAT_UNDANG);

                    textKelengkapan.setText(KELENGKAPAN);
                    textNipKelengkapan.setText(NIP_KELENGKAPAN);
                    textTingkatKelengkapan.setText(TINGKAT_KELENGKAPAN);

                    textBendahara.setText(BENDAHARA);
                    textNipBendahara.setText(NIP_BENDAHARA);
                    textTingkatBendahara.setText(TINGKAT_BENDAHARA);



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
