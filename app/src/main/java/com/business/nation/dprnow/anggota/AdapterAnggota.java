package com.business.nation.dprnow.anggota;


import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterAnggota extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelAnggota> evenprolist;

    public AdapterAnggota(Activity activity, List<ModelAnggota> evenprolist) {
        this.activity = activity;
        this.evenprolist = evenprolist;
    }

    @Override
    public int getCount() {
        return evenprolist.size();
    }

    @Override
    public Object getItem(int position) {
        return evenprolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.adapter_anggota, null);

        ImageView img_android = (ImageView) convertView.findViewById(R.id.imgAnggota);
        TextView textNamaAnggota = (TextView) convertView.findViewById(R.id.textNamaAnggota) ;
        TextView textPartaiAnggota = (TextView) convertView.findViewById(R.id.textPartaiAnggota) ;
        TextView textPosisiAnggota = (TextView) convertView.findViewById(R.id.textPosisiAnggota) ;
        TextView textNIKAnggota = (TextView) convertView.findViewById(R.id.textNIKAnggota) ;

        ModelAnggota items = evenprolist.get(position);

        /*Typeface txtFont = Typeface.createFromAsset(activity.getAssets(),
                "Montserrat-Regular.ttf");
        Typeface txtBold = Typeface.createFromAsset(activity.getAssets(),
                "Montserrat-SemiBold.ttf");*/


        textNamaAnggota.setText(items.getNAMA());
        textPartaiAnggota.setText(items.getNAMA());
        textPosisiAnggota.setText(items.getPOSISI());
        textNIKAnggota.setText(items.getNIK());

        String imageAnggota= "https://dprd.gresikkab.go.id/dprd/foto/"+items.getFOTO();

        Glide.with(activity)
                .load(imageAnggota)
                .into(img_android);


        return convertView;
    }

    public String konvertTanggal(String dates){
        String finalDateString="";
        String strDate = "2017-05-23T06:25:50";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dates);
            SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd MMMM yyyy");
            finalDateString = sdfnewformat.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finalDateString;
    }
}