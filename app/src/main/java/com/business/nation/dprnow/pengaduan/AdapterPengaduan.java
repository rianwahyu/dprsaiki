package com.business.nation.dprnow.pengaduan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.business.nation.dprnow.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterPengaduan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<HashMap<String,String>> mDataset;
    private List<AdapterPengaduan> listPengaduan ;
    private Context context;
    ViewHolder viewHolder;

    private OnItemClickListener listener;

    /*public interface OnItemClickListener {
        void onItemClick(ContentItem item);
    }*/

    public interface OnItemClickListener {
        void onItemClick(HashMap<String, String> item);
    }

    public void add(int position, HashMap<String,String> item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove( HashMap<String,String> item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }






    public AdapterPengaduan(Context context, ArrayList<HashMap<String, String>> myDataset, RecyclerView recyclerView) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_pengaduan, viewGroup, false);

        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        HashMap<String,String> map = mDataset.get(position);

        ViewHolder holder   =  (ViewHolder) viewHolder;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((Activity)context),DetailPengaduan.class);

                ((Activity)context).startActivity(intent);
            }
        });
    }


    public void setOnItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgUser;
        TextView textUser, textLocation, textTitle, textIsi,textLike, textComment, textTime;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardPengaduan);
        }

        public void bind(final HashMap<String,String> item, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }


}
