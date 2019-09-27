package com.business.nation.dprnow.berita;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.agenda.AdapterAgenda;
import com.business.nation.dprnow.aspirasi.DetailAspirasi;
import com.business.nation.dprnow.aspirasi.ModelAspirasi;
import com.business.nation.dprnow.util.NetworkState;

import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelBerita> mDataset;


    public AdapterBerita(Context context, List<ModelBerita> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_berita, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders) {
            ViewHolders viewHolders = (ViewHolders) viewHolder;

            viewHolders.textTanggal.setText(mDataset.get(position).getTANGGAL());
            viewHolders.textJam.setText(mDataset.get(position).getJAM());
            viewHolders.textJudul.setText(mDataset.get(position).getJUDUL());

            viewHolders.textTempat.setText(mDataset.get(position).getTEMPAT());

            String isi = mDataset.get(position).getDESKRIPSI();
            /*String myHtmlStringDeskripsi = "<html><body><p align='justify'>" +
                    isi +
                    "</p></body></html>";
            viewHolders.textIsi.loadData(myHtmlStringDeskripsi, "text/html", null);*/

            viewHolders.textIsi.setText(isi);

            final String id = mDataset.get(position).getID();

            ((ViewHolders) viewHolder).frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailBerita.class);
                    intent.putExtra("id", id);
                    ((v.getContext())).startActivity(intent);
                }
            });

            String photo = NetworkState.getUrlDir()+"foto/beritadprd/"+mDataset.get(position).getFILE_FOTO();
            Glide.with(context)
                    .load(photo)
                    .into(viewHolders.imgBerita);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{
        TextView textTanggal, textJam, textJudul, textTempat;
        FrameLayout frameLayout;
        TextView textIsi;
        ImageView imgBerita;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.textTanggalBerita);
            textJam = itemView.findViewById(R.id.textJamBerita);
            textJudul = itemView.findViewById(R.id.textJudulBerita);
            textIsi = itemView.findViewById(R.id.textIsiBerita);
            textTempat = itemView.findViewById(R.id.textTempatBerita);

            frameLayout = itemView.findViewById(R.id.frameBerita);

            imgBerita = itemView.findViewById(R.id.imgBerita);
        }
    }


}
