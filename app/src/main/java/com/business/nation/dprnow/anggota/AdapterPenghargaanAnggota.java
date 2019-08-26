package com.business.nation.dprnow.anggota;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.business.nation.dprnow.R;

import java.util.List;

public class AdapterPenghargaanAnggota extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelPenghargaanAnggota> mDataset;


    public AdapterPenghargaanAnggota(Context context, List<ModelPenghargaanAnggota> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_pendidikan_anggota, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders) {
            ViewHolders viewHolders = (ViewHolders) viewHolder;
            String nama = mDataset.get(position).getNAMA();
            String tahun = mDataset.get(position).getTAHUN();
            ((ViewHolders) viewHolder).textNama.setText(nama+", Tahun : "+ tahun);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{
        TextView textNama;
        FrameLayout frameLayout;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.textPendidikan);
        }
    }


}
