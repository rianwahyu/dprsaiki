package com.business.nation.dprnow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.business.nation.dprnow.fragment.FragmentInformasi;
import com.business.nation.dprnow.komisi.FragmentPengaduanList;
import com.business.nation.dprnow.fragment.FragmentStreaming;
import com.business.nation.dprnow.fragment.fragmentHome;
import com.business.nation.dprnow.util.AppController;
import com.business.nation.dprnow.util.NetworkState;
import com.business.nation.dprnow.util.SessionManager;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation, bottomNavigationRight;
    private Fragment fragment, fragmentRight;
    private FragmentManager fragmentManager;

    FloatingActionButton fabAdd;
    Context context=MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cekUpdate();

        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog();
            }
        });

        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new fragmentHome()).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.bottom_home:
                        fragment = new fragmentHome();
                        break;

                    case R.id.bottom_category:
                        fragment = new FragmentPengaduanList();
                        break;

                    case R.id.bottom_streaming:
                        fragment = new FragmentStreaming();
                        break;

                    case R.id.bottom_info:
                        fragment = new FragmentInformasi();
                        break;
                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });

    }

    private void loadDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_pilih_tambah, null);
        Button btnAspirasi = dialogView.findViewById(R.id.buttonAspirasi);
        Button btnBerita = dialogView.findViewById(R.id.buttonBerita);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnBerita.setVisibility(View.GONE);
        btnBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TambahBeritaActivity.class));
                finish();
                alertDialog.dismiss();
            }
        });


        btnAspirasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TambahAspirasiActivity.class));
                finish();
                alertDialog.dismiss();
            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        loadExit();
    }

    private void loadExit() {
        new FancyAlertDialog.Builder(this)
                .setTitle("Keluar Aplikasi")
                .setBackgroundColor(Color.parseColor("#DC110E"))  //Don't pass R.color.colorvalue
                .setMessage("Apakah Anda Ingin Keluar dari Aplikasi DPR Now ?")
                .setNegativeBtnText("Batal")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ya")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_info_white, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        finish();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                })
                .build();
    }

    int sukses;
    String versiPhone="1.0";
    public void cekUpdate(){
        String url = NetworkState.getUrlCek()+"cek_dprd.php" ;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jObj = null;
                        try {
                            jObj = new JSONObject(response);
                            sukses = jObj.getInt("response");
                            String versi = jObj.getString("versi");

                            if (!versi.equals(versiPhone)){
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Maintenance");
                                builder.setMessage("Mohon Maaf Aplikasi Dalam Maintenance");
                                builder.setCancelable(false);
                                builder.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
