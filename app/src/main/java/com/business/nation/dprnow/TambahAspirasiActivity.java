package com.business.nation.dprnow;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.business.nation.dprnow.aspirasi.CatModel;
import com.business.nation.dprnow.util.DialogHelper;
import com.business.nation.dprnow.util.NetworkState;
import com.business.nation.dprnow.util.PermissionHelper;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TambahAspirasiActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etJudulAspirasi, etDeskripsiAspirasi;
    Button btnSubmit;
    ImageView imgFoto;
    Context context = TambahAspirasiActivity.this;
    AlertDialog successDialog;

    Bitmap bitmap, decoded;
    PermissionHelper permissionHelper;

    private final int GALLERY = 1;

    private Spinner spinner;
    // array list for spinner adapter
    private ArrayList<CatModel> listCategory;
    ProgressDialog pDialog;

    String ids="";

    private String URL_CATEGORIES = NetworkState.getUrl()+"get_data_kategori_pengaduan.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_aspirasi);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_black);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etJudulAspirasi = findViewById(R.id.et_judulAspirasi);
        etDeskripsiAspirasi = findViewById(R.id.et_deskripsiAspirasi);
        imgFoto = findViewById(R.id.imgFoto);

        spinner = findViewById(R.id.spinCategory);

        listCategory = new ArrayList<CatModel>();

        // spinner item select listener
        spinner.setOnItemSelectedListener(this);

        requestMultiplePermissions();

        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, GALLERY);
            }
        });


        btnSubmit = findViewById(R.id.btnSubmitBerita);

        permissionHelper = new PermissionHelper(this);
        checkAndRequestPermissions();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = etJudulAspirasi.getText().toString();
                String deskripsi = etDeskripsiAspirasi.getText().toString();

                if (judul.isEmpty()||deskripsi.isEmpty()){
                    Toast.makeText(context, "Mohon Melengkapi Form" , Toast.LENGTH_SHORT).show();
                }else{
                    uploadAspirasi(judul,deskripsi);
                }
            }
        });

        //new GetCategories().execute();
        initCagegory();
    }

    private void initCagegory() {
        String url = NetworkState.getUrl()+"get_data_kategori_pengaduan/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject asp = response.getJSONObject(i);

                        String ID =asp.getString("ID");
                        String NAMA =asp.getString("NAMA");



                        CatModel mb = new CatModel();
                        mb.setID(ID);
                        mb.setNAMA(NAMA);

                        listCategory.add(mb);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //adapterAnggota.notifyDataSetChanged();
                populateSpinner();
                /*Toast.makeText(getActivity(), "Ada", Toast.LENGTH_SHORT).show();*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahAspirasiActivity.this, "Eror", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(TambahAspirasiActivity.this);
        requestQueue.add(jsonArrayRequest);
    }


    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();

        for (int i = 0; i < listCategory.size(); i++) {
            lables.add(listCategory.get(i).getNAMA());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CatModel cm = listCategory.get(position);
        ids = cm.getID();
        /*Toast.makeText(
                getApplicationContext(),
                ids + " Selected" ,
                Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "ImgSuper" + timeStamp + ".jpg");

        return mediaFile;
    }

    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {
                //Toast.makeText(PengajuanActivity.this,"Kamera disetujui", Toast.LENGTH_LONG).show();
            }
        });

        permissionHelper.checkAndRequestPermissions();

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequsetCallBack(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    // imageView.setImageBitmap(bitmap);
                    imgFoto.setImageBitmap(bitmap);
                    //uploadImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void uploadAspirasi(final String judul, final String deskripsi){
        /*String url = "https://dprd.gresikkab.go.id/dprd/auth/input_aspirasi";*/
        String url = NetworkState.getUrl()+"input_aspirasi";
        final ProgressDialog loading = ProgressDialog.show(context,"Upload","Please Wait..", false,false);
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String res = new String(response.data);
                if (res.equals("true")){
                    loading.dismiss();
                    successDialog = DialogHelper.successDialog(context,"Data Aspirasi Berhasil disimpan", new DialogHelper.OnOk(){
                        @Override
                        public void onOk(AlertDialog alertDialog, View view) {
                            alertDialog.dismiss();
                            finish();
                        }
                    });
                    successDialog.show();
                }else{
                    loading.dismiss();
                    Toast.makeText(context,"Mohon Maaf, Proses Tambah Aspirasi Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(context,"gagal", Toast.LENGTH_SHORT);
            }
        })
        {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("judul", judul);
                params.put("kategori", ids);
                params.put("deskripsi", deskripsi);
                params.put("user", "nimas");
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("foto[]", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}
