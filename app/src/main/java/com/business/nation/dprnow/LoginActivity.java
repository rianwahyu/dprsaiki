package com.business.nation.dprnow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.business.nation.dprnow.util.AppController;
import com.business.nation.dprnow.util.NetworkState;
import com.business.nation.dprnow.util.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    AppCompatButton btnLogin;
    Context context = LoginActivity.this;

    AlertDialog dialogLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(context, "Mohon Melengkapi Username / Password Anda", Toast.LENGTH_SHORT).show();
                }else {
                    processLogin(username, password);
                }
            }
        });
    }

    private void processLogin(final String username, final String password) {
        String url = NetworkState.getUrl()+ "login" ;
        /*dialogLogin = DialogHelper.loading(this, "Logging in..");
        dialogLogin.show();*/

        final ProgressDialog loading = ProgressDialog.show(context,"Logging In","Please Wait..", false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    //dialogLogin.dismiss();
                    JSONObject jObj = null;
                    try {
                        jObj = new JSONObject(response);
                        String usernames = jObj.getString("username");
                        String passwords = jObj.getString("password");
                        String pesan = jObj.getString("pesan");
                        String id_user = jObj.getString("id_user");
                        String hasil = jObj.getString("hasil");
                        Toast.makeText(context, "Berhasil Login", Toast.LENGTH_SHORT).show();
                        new SessionManager(context).login(usernames, passwords, pesan,id_user, hasil);
                        /*dialogLogin.dismiss();*/
                        loading.dismiss();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                loading.dismiss();
                /*dialogLogin.dismiss();*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialogLogin.show();
                Toast.makeText(context, "Gagal Login", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",username );
                params.put("password",password );
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
