package com.business.nation.dprnow.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.nation.dprnow.R;
import com.business.nation.dprnow.anggota.AnggotaActivity;
import com.business.nation.dprnow.aspirasi.DetailAspirasi;

public class FragmentInformasi extends Fragment {

    CardView cardAnggota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informasi, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardAnggota = view.findViewById(R.id.cardDaftarAnggota);

        cardAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AnggotaActivity.class);

                ((v.getContext())).startActivity(intent);
            }
        });
    }
}
