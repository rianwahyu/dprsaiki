package com.business.nation.dprnow.regulasi;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.anggota.DetailAnggota;
import com.business.nation.dprnow.aspirasi.ItemClickListener;
import com.business.nation.dprnow.util.CheckForSDCard;
import com.business.nation.dprnow.util.NetworkState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class RegulasiActivity extends AppCompatActivity implements ItemClickListener, EasyPermissions.PermissionCallbacks {

    RecyclerView recyclerView;
    private List<ModelRegulasi> listNaskah = new ArrayList<ModelRegulasi>();
    AdapterRegulasi2 adapterRegulasi;
    Context context = RegulasiActivity.this;
    private static final int WRITE_REQUEST_CODE = 300;

    String urlFILES;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulasi);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RegulasiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listNaskah = new ArrayList<ModelRegulasi>();
        recyclerView = findViewById(R.id.listRegulasi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterRegulasi = new AdapterRegulasi2(RegulasiActivity.this, listNaskah);
        recyclerView.setAdapter(adapterRegulasi);
        initRegulasi();
        adapterRegulasi.setClickListener(this);
    }

    private void initRegulasi() {
        String url = NetworkState.getUrl()+"get_data_regulasi/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String NAMA =asp.getString("NAMA");
                        String ISI =asp.getString("ISI");
                        String FILE_PDF =asp.getString("FILE_PDF");
                        String urlPDF= NetworkState.getUrlPDF()+"/peraturan/"+FILE_PDF;

                        ModelRegulasi mb = new ModelRegulasi();
                        mb.setID(ID);
                        mb.setNAMA(NAMA);
                        mb.setISI(ISI);
                        mb.setFILE_PDF(urlPDF);
                        listNaskah.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterRegulasi.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegulasiActivity.this, "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(RegulasiActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view, int position) {
        ModelRegulasi mr = listNaskah.get(position);
        String id = mr.getID();
        urlFILES = mr.getFILE_PDF();

        if (CheckForSDCard.isSDCardPresent()) {

            //check if app has permission to write to the external storage.
            if (EasyPermissions.hasPermissions(RegulasiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Get the URL entered

                new DownloadFile().execute(urlFILES);

            } else {
                //If permission is not present request for the same.
                EasyPermissions.requestPermissions(RegulasiActivity.this, getString(R.string.write_file), WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }


        } else {
            Toast.makeText(getApplicationContext(),
                    "SD Card not found", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, RegulasiActivity.this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        new DownloadFile().execute(urlFILES);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("TAG", "Permission has been denied");
    }

    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(RegulasiActivity.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                fileName = timestamp + "_" + fileName;

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "dprnow/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d("TAG", "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();
        }
    }

}
