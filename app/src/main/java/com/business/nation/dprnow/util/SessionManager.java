package com.business.nation.dprnow.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.business.nation.dprnow.BuildConfig;

public class SessionManager {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void login(String username, String password, String pesan, String hasil, String id_user) {
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("pesan", pesan);
        editor.putString("hasil", hasil);
        editor.putString("id_user", id_user);
        editor.putBoolean("logged", true);
        editor.commit();
    }

    public void logout() {
        editor.putString("username", "");
        editor.putString("password", "");
        editor.putString("pesan", "");
        editor.putString("hasil", "");
        editor.putString("id_user", "");
        editor.putBoolean("logged", false);
        editor.commit();
    }

    public boolean isLoggedin() {
        return preferences.getBoolean("logged", false);
    }
}
