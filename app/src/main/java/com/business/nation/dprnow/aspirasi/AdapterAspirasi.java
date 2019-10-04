package com.business.nation.dprnow.aspirasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.agenda.ModelAgenda;
import com.business.nation.dprnow.util.NetworkState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterAspirasi extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    private Context context;
    private List<ModelAspirasi> mDataset;
    private ItemClickListener itemClickListener;

    public AdapterAspirasi(Context context, List<ModelAspirasi> myDataset) {
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
                R.layout.adapter_aspirasi, viewGroup, false);
        ViewHolders viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders){
            ViewHolders viewHolders = (ViewHolders) viewHolder;

            Typeface typeTitle = Typeface.createFromAsset(context.getAssets(),
                    "Roboto-Bold.ttf");
            Typeface typeUser = Typeface.createFromAsset(context.getAssets(),
                    "Roboto-Regular.ttf");

            viewHolders.textTanggal.setText(mDataset.get(position).getTANGGAL());
            viewHolders.textJudul.setText(mDataset.get(position).getJUDUL());
            viewHolders.textIsi.setText(mDataset.get(position).getISI());
            viewHolders.textStatus.setText(mDataset.get(position).getSTATUS());
            viewHolders.textLike.setText(mDataset.get(position).getLIKE());
            viewHolders.textUnlike.setText(mDataset.get(position).getUNLIKE());
            viewHolders.textComment.setText(mDataset.get(position).getCOMMENT());

            String isi = mDataset.get(position).getISI();
/*            String myHtmlStringDeskripsi = "<html><body><p align='justify'>" +
                    isi +
                    "</p></body></html>";
            viewHolders.textIsi.loadData(myHtmlStringDeskripsi, "text/html", null);*/



            /*String photo = "https://dprd.gresikkab.go.id/dprd/foto/aspirasi/"+mDataset.get(position).getFOTO();*/
            String photo = NetworkState.getUrlDir()+"foto/aspirasi/"+mDataset.get(position).getFOTO();
            Glide.with(context)
                    .load(photo)
                    .into(viewHolders.imgAspirasi);

            final String id = mDataset.get(position).getID();

            viewHolders.textUser.setText(mDataset.get(position).getUSER());

            viewHolders.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailAspirasi.class);
                    intent.putExtra("id", id);
                    ((v.getContext())).startActivity(intent);
                }
            });

            viewHolders.textJudul.setTypeface(typeTitle);
            viewHolders.textStatus.setTypeface(typeUser);
            viewHolders.textUser.setTypeface(typeUser);
            viewHolders.textIsi.setTypeface(typeUser);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public  class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textTanggal, textJudul,  textStatus, textLike, textUnlike, textComment, textUser;
        ImageView imgAspirasi;
        CardView cardView;
        TextView textIsi;
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
            cardView = itemView.findViewById(R.id.cardAspirasi);

            textUser = itemView.findViewById(R.id.textUserAspirasi);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }


}
