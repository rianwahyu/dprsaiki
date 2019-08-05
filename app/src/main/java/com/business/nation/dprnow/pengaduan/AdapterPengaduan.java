package com.business.nation.dprnow.pengaduan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.nation.dprnow.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterPengaduan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AdapterPengaduan> listPengaduan ;
    private Context context;
    ViewHolder viewHolder;

    private ArrayList<HashMap<String,String>> mDataset;


    public AdapterPengaduan(Context context, ArrayList<HashMap<String, String>> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_pengaduan, viewGroup, false);

        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgUser;
        TextView textUser, textLocation, textTitle, textIsi,textLike, textComment, textTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
