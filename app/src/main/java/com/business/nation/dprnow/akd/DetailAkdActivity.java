package com.business.nation.dprnow.akd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    textTingkatHumas;

    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akd);
        textNamaSekretaris = findViewById(R.id.textNamaSekretaris);
        textNipSekretaris = findViewById(R.id.textNIPSekretaris);
        textTingkatSekretaris = findViewById(R.id.textTingkatSekretaris);
        textUmum = findViewById(R.id.textNamaUmum);
        textNipUmum = findViewById(R.id.textNIPUmum);
        textTingkatUmum = findViewById(R.id.textTingkatUmum);
        textKeuangan = findViewById(R.id.textNamaKeuangan);
        textNipKeuangan = findViewById(R.id.textNIPKeuangan);
        textTingkatHumas = findViewById(R.id.textTingkatKeuangan);
        textHumas = findViewById(R.id.textNamaHumas);
        textNipHumas = findViewById(R.id.textNIPHumas);
        textTingkatHumas = findViewById(R.id.textTingkatHumas);

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

                    textNamaSekretaris.setText(SEKERTARIS);
                    textNipSekretaris.setText(NIP_SEKERTARIS);
                    textTingkatSekretaris.setText(TINGKAT_SEKERTARIS);

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
}
