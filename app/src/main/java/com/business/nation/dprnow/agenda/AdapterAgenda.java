package com.business.nation.dprnow.agenda;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.nation.dprnow.R;

import java.util.List;

public class AdapterAgenda extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelAgenda> mDataset;


    public AdapterAgenda(Context context, List<ModelAgenda> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_agenda, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders) {

            Typeface typeTitle = Typeface.createFromAsset(context.getAssets(),
                    "Roboto-Bold.ttf");
            Typeface typeUser = Typeface.createFromAsset(context.getAssets(),
                    "Roboto-Regular.ttf");

            ViewHolders viewHolders = (ViewHolders) viewHolder;
            viewHolders.textTanggal.setText(mDataset.get(position).getTANGGAL());
            viewHolders.textJam.setText(mDataset.get(position).getJAM());
            viewHolders.textJudul.setText(mDataset.get(position).getJUDUL());
            viewHolders.textIsi.setText(mDataset.get(position).getDESKRIPSI());
            viewHolders.textTempat.setText(mDataset.get(position).getTEMPAT());

            viewHolders.textJudul.setTypeface(typeTitle);
            viewHolders.textIsi.setTypeface(typeUser);
            viewHolders.textTempat.setTypeface(typeUser);
            viewHolders.textJam.setTypeface(typeUser);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public  class ViewHolders extends RecyclerView.ViewHolder{

        TextView textTanggal, textJam, textJudul, textIsi, textTempat;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.textTanggalAgenda);
            textJam = itemView.findViewById(R.id.textJamAgenda);
            textJudul = itemView.findViewById(R.id.textJudulAgenda);
            textIsi = itemView.findViewById(R.id.textIsiAgenda);
            textTempat = itemView.findViewById(R.id.textTempatAgenda);

        }
    }


}
