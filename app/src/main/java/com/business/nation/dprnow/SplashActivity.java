package com.business.nation.dprnow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.business.nation.dprnow.util.SessionManager;

public class SplashActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    Context context = SplashActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (new SessionManager(context).isLoggedin()) {
                    Intent o = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(o);
                    finish();
                } else {
                    Intent o = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(o);
                    finish();
                }
            }
        };

        handler.postDelayed(runnable, 1500);
    }
}
