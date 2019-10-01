package com.business.nation.dprnow.anggota;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.aspirasi.ItemClickListener;
import com.business.nation.dprnow.naskah_akademik.ModelNaskahAkademik;
import com.business.nation.dprnow.util.NetworkState;

import java.util.List;

public class AdapterAnggota2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelAnggota> mDataset;
    private ItemClickListener itemClickListener;


    public AdapterAnggota2(Context context, List<ModelAnggota> myDataset) {
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
                R.layout.adapter_anggota, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof ViewHolders) {
            ViewHolders viewHolders = (ViewHolders) viewHolder;

            viewHolders.textNamaAnggota.setText(mDataset.get(position).getNAMA());
            viewHolders.textPartai.setText(mDataset.get(position).getPARTAI());
            viewHolders.textPosisi.setText(mDataset.get(position).getPOSISI());
            viewHolders.textNIK.setText(mDataset.get(position).getNIK());


            String imageAnggota= NetworkState.getUrlDir()+ "foto/"+mDataset.get(position).getFOTO();

            Glide.with(context)
                    .load(imageAnggota)
                    .into(viewHolders.imgAnggota);

            viewHolders.cardView.setOnClickListener(new View.OnClickListener() {
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
        TextView textNamaAnggota, textPartai, textPosisi, textNIK;
        ImageView imgAnggota;
        CardView cardView;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            textNamaAnggota = itemView.findViewById(R.id.textNamaAnggota);
            textPartai = itemView.findViewById(R.id.textPartaiAnggota);
            textPosisi = itemView.findViewById(R.id.textPosisiAnggota);
            textNIK = itemView.findViewById(R.id.textNIKAnggota);
            imgAnggota = itemView.findViewById(R.id.imgAnggota);

            cardView = itemView.findViewById(R.id.cardAnggota);
        }
    }


}
