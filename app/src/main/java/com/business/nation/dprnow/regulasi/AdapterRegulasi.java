package com.business.nation.dprnow.regulasi;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.naskah_akademik.ModelNaskahAkademik;

import java.util.List;

public class AdapterRegulasi extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelRegulasi> evenprolist;

    public AdapterRegulasi(Activity activity, List<ModelRegulasi> evenprolist) {
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

        ModelRegulasi items = evenprolist.get(position);

        /*Typeface txtFont = Typeface.createFromAsset(activity.getAssets(),
                "Montserrat-Regular.ttf");
        Typeface txtBold = Typeface.createFromAsset(activity.getAssets(),
                "Montserrat-SemiBold.ttf");*/


        textNamaNaskah.setText(items.getNAMA());
        textIsiNaskah.setText(items.getISI());




        return convertView;
    }
}