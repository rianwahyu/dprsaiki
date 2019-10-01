package com.business.nation.dprnow.regulasi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.anggota.ModelOrganisasiAnggota;
import com.business.nation.dprnow.aspirasi.ItemClickListener;

import java.util.List;

public class AdapterRegulasi2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelRegulasi> mDataset;
    private ItemClickListener itemClickListener;


    public AdapterRegulasi2(Context context, List<ModelRegulasi> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_regulasi, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof ViewHolders) {
            ViewHolders viewHolders = (ViewHolders) viewHolder;
            String nama = mDataset.get(position).getNAMA();
            String isi = mDataset.get(position).getISI();

            viewHolders.textNama.setText(nama);
            viewHolders.textIsi.setText(isi);

            viewHolders.btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) itemClickListener.onClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{
        TextView textNama, textIsi;
        Button btnDownload;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            textNama = itemView.findViewById(R.id.textNamaRegulasi);
            textIsi = itemView.findViewById(R.id.textIsiRegulasi);
            btnDownload = itemView.findViewById(R.id.btnDownloadPdf);
        }
    }


}
