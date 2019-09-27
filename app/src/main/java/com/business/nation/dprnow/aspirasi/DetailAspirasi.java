package com.business.nation.dprnow.aspirasi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.util.AppController;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailAspirasi extends AppCompatActivity {

    TextView textUser, textTanggal, textJam, textJudul, textStatus, textLike
            ,textUnlike, textComment;
    ImageView imgAspirasi;
    WebView textIsi;
    String id;

    Context context=DetailAspirasi.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aspirasi);

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

        textUser = findViewById(R.id.textUser);
        textTanggal = findViewById(R.id.textTanggalAspirasi);
        textJudul = findViewById(R.id.textJudulAspirasi);
        textIsi = findViewById(R.id.textIsiAspirasi);
        textStatus = findViewById(R.id.textStatusAspirasi);
        textLike = findViewById(R.id.textCountLikeAspirasi);
        textUnlike = findViewById(R.id.textCountunlikeAspirasi);
        textComment = findViewById(R.id.textCountunCommentAspirasi);
        imgAspirasi = findViewById(R.id.imgAspirasi);

        id = getIntent().getStringExtra("id");

        getDetailAspirasi(id);
    }

    public void getDetailAspirasi(final String id){
        String url = NetworkState.getUrl()+ "get_data_single_aspirasi/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(response);

                    String ID = jObj.getString("ID");
                    String ID_KATEGORI = jObj.getString("ID_KATEGORI");
                    String JUDUL = jObj.getString("JUDUL");
                    String TANGGAL = jObj.getString("TANGGAL");
                    String ISI = jObj.getString("ISI");
                    String JAM = jObj.getString("JAM");
                    String USER = jObj.getString("USER");
                    String STATUS = jObj.getString("STATUS");
                    String LIKE = jObj.getString("LIKE");
                    String UNLIKE = jObj.getString("UNLIKE");
                    String COMMENT = jObj.getString("COMMENT");
                    String FOTO = jObj.getString("FOTO");

                    textJudul.setText(JUDUL);
                    textUser.setText(USER);
                    textTanggal.setText(TANGGAL + ", " +JAM);
                    textLike.setText(LIKE);
                    textComment.setText(COMMENT);
                    textUnlike.setText(UNLIKE);
                    textStatus.setText(STATUS);


                    String myHtmlStringDeskripsi = "<html><body><p align='justify'>" +
                            ISI +
                            "</p></body></html>";
                    textIsi.loadData(myHtmlStringDeskripsi, "text/html", null);
                    /*String photo = "https://dprd.gresikkab.go.id/dprd/foto/aspirasi/"+FOTO;*/
                    String photo = NetworkState.getUrlDir()+"foto/aspirasi/"+FOTO;
                    Glide.with(context)
                            .load(photo)
                            .into(imgAspirasi);


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
