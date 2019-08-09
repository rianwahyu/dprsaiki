package com.business.nation.dprnow;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.business.nation.dprnow.fragment.FragmentInformasi;
import com.business.nation.dprnow.fragment.FragmentKomisi;
import com.business.nation.dprnow.fragment.FragmentStreaming;
import com.business.nation.dprnow.fragment.fragmentHome;
import com.business.nation.dprnow.util.BottomNavigationHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation, bottomNavigationRight;
    private Fragment fragment, fragmentRight;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomnavLeft);
        //BottomNavigationHelper.disableShiftMode(bottomNavigation);
        //bottomNavigation.inflateMenu(R.menu.main);
        fragmentManager = getSupportFragmentManager();

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
                        fragment = new FragmentKomisi();
                        break;

                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });

        bottomNavigationRight = (BottomNavigationView) findViewById(R.id.bottomnavRigth);
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
        });
    }
}
