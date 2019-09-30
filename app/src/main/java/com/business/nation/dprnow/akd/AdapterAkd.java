package com.business.nation.dprnow.akd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.aspirasi.ItemClickListener;

import java.util.List;

public class AdapterAkd extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ModelAkd> mDataset;
    private ItemClickListener itemClickListener;

    public AdapterAkd(Context context, List<ModelAkd> myDataset) {
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
                R.layout.adapter_akd, viewGroup, false);
        ViewHolders viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof ViewHolders){
            ViewHolders viewHolders = (ViewHolders) viewHolder;

            viewHolders.textSekretaris.setText(mDataset.get(position).getSEKERTARIS());
            viewHolders.textUmum.setText(mDataset.get(position).getUMUM());
            viewHolders.textKeuangan.setText(mDataset.get(position).getKEUANGAN());
            viewHolders.textHumas.setText(mDataset.get(position).getHUMAS());
            viewHolders.textPersidangan.setText(mDataset.get(position).getPERSIDANGAN());

            viewHolders.teextAKD.setOnClickListener(new View.OnClickListener() {
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

    public  class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textSekretaris, textUmum,  textKeuangan, textHumas, textPersidangan, teextAKD;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);


            textSekretaris = itemView.findViewById(R.id.textNamaSekretaris);
            textUmum = itemView.findViewById(R.id.textNamaUmum);
            textKeuangan = itemView.findViewById(R.id.textNamaKeuangan);
            textHumas = itemView.findViewById(R.id.textNamaHumas);
            textPersidangan = itemView.findViewById(R.id.textNamaPersidangan);
            teextAKD = itemView.findViewById(R.id.textDetailAKD);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }


}
