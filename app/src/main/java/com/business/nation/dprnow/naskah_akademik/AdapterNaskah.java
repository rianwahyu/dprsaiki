package com.business.nation.dprnow.naskah_akademik;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.anggota.ModelAnggota;
import com.business.nation.dprnow.util.NetworkState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterNaskah extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelNaskahAkademik> evenprolist;

    public AdapterNaskah(Activity activity, List<ModelNaskahAkademik> evenprolist) {
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
            convertView = inflater.inflate(R.layout.adapter_naskah, null);

        TextView textNamaNaskah = (TextView) convertView.findViewById(R.id.textNamaNaskah) ;
        TextView textIsiNaskah = (TextView) convertView.findViewById(R.id.textISINaskah) ;

        ModelNaskahAkademik items = evenprolist.get(position);

        /*Typeface txtFont = Typeface.createFromAsset(activity.getAssets(),
                "Montserrat-Regular.ttf");
        Typeface txtBold = Typeface.createFromAsset(activity.getAssets(),
                "Montserrat-SemiBold.ttf");*/


        textNamaNaskah.setText(items.getNAMA());
        textIsiNaskah.setText(items.getISI());




        return convertView;
    }
}