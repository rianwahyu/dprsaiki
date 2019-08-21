package com.business.nation.dprnow.aspirasi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.agenda.ModelAgenda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterAspirasi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelAspirasi> mDataset;


    public AdapterAspirasi(Context context, List<ModelAspirasi> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_aspirasi, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders){
            ViewHolders viewHolders = (ViewHolders) viewHolder;

            viewHolders.textTanggal.setText(mDataset.get(position).getTANGGAL());
            viewHolders.textJudul.setText(mDataset.get(position).getJUDUL());
            viewHolders.textIsi.setText(mDataset.get(position).getISI());
            viewHolders.textStatus.setText(mDataset.get(position).getSTATUS());
            viewHolders.textLike.setText(mDataset.get(position).getLIKE());
            viewHolders.textUnlike.setText(mDataset.get(position).getUNLIKE());
            viewHolders.textComment.setText(mDataset.get(position).getCOMMENT());

            String photo = "https://dprd.gresikkab.go.id/dprd/foto/aspirasi/"+mDataset.get(position).getFOTO();
            Glide.with(context)
                    .load(photo)
                    .into(viewHolders.imgAspirasi);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{

        TextView textTanggal, textJudul, textIsi, textStatus, textLike, textUnlike, textComment;
        ImageView imgAspirasi;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            textTanggal = itemView.findViewById(R.id.textTanggalAspirasi);
            textJudul = itemView.findViewById(R.id.textJudulAspirasi);
            textIsi = itemView.findViewById(R.id.textIsiAspirasi);
            textStatus = itemView.findViewById(R.id.textStatusAspirasi);
            textLike = itemView.findViewById(R.id.textCountLikeAspirasi);
            textUnlike = itemView.findViewById(R.id.textCountunlikeAspirasi);
            textComment = itemView.findViewById(R.id.textCountunCommentAspirasi);

            imgAspirasi = itemView.findViewById(R.id.imgAspirasi);
        }
    }


}
