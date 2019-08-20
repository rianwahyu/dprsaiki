package com.business.nation.dprnow.berita;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.aspirasi.ModelAspirasi;

import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolder viewHolder;

    private List<ModelBerita> mDataset;


    public AdapterBerita(Context context, List<ModelBerita> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_aspirasi, viewGroup, false);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
