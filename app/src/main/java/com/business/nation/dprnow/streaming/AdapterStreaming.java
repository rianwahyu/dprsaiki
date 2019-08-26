package com.business.nation.dprnow.streaming;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.anggota.ModelOrganisasiAnggota;
import com.business.nation.dprnow.util.Constant;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class AdapterStreaming extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ViewHolders viewHolder;

    private List<ModelStreaming> mDataset;


    public AdapterStreaming(Context context, List<ModelStreaming> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_list_youtube, viewGroup, false);
        viewHolder = new ViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolders) {
            ViewHolders viewHolders = (ViewHolders) viewHolder;
            String nama = mDataset.get(position).getNAMA();
            String url = mDataset.get(position).getURL();

            viewHolders.videoThumbnailImageView.initialize(Constant.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolders extends RecyclerView.ViewHolder{
        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle, videoDuration;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            videoTitle = itemView.findViewById(R.id.video_title_label);
            videoDuration = itemView.findViewById(R.id.video_duration_label);
        }
    }


}
