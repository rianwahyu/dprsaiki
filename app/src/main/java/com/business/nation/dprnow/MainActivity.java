package com.business.nation.dprnow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.business.nation.dprnow.fragment.FragmentInformasi;
import com.business.nation.dprnow.komisi.FragmentPengaduanList;
import com.business.nation.dprnow.fragment.FragmentStreaming;
import com.business.nation.dprnow.fragment.fragmentHome;

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

        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        //BottomNavigationHelper.disableShiftMode(bottomNavigation);
        //bottomNavigation.inflateMenu(R.menu.main);
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

        /*bottomNavigationRight = (BottomNavigationView) findViewById(R.id.bottomnavRigth);
        //BottomNavigationHelper.disableShiftMode(bottomNavigationRight);

        bottomNavigationRight.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.bottom_streaming:
                        fragmentRight = new FragmentStreaming();
                        break;

                    case R.id.bottom_info:
                        fragmentRight = new FragmentInformasi();
                        break;
                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragmentRight).commit();
                return true;
            }
        });*/
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
                alertDialog.dismiss();
            }
        });


        btnAspirasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TambahAspirasiActivity.class));
                alertDialog.dismiss();
            }
        });
    }
}
