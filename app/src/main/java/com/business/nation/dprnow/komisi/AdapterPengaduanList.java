package com.business.nation.dprnow.komisi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.berita.DetailBerita;
import com.business.nation.dprnow.berita.ModelBerita;

import java.util.List;

public class AdapterPengaduanList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelPengaduanList> mDataset;


    public AdapterPengaduanList(Context context, List<ModelPengaduanList> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_pengaduan_list, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders) {
            ViewHolders viewHolders = (ViewHolders) viewHolder;

            viewHolders.textPengaduan.setText(mDataset.get(position).getNAMA());
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{
        TextView textPengaduan;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            textPengaduan = itemView.findViewById(R.id.textPengaduan);
        }
    }


}
