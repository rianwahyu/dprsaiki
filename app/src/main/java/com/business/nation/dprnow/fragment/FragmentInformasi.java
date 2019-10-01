package com.business.nation.dprnow.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.nation.dprnow.LoginActivity;
import com.business.nation.dprnow.MainActivity;
import com.business.nation.dprnow.R;
import com.business.nation.dprnow.akd.AkdActivity;
import com.business.nation.dprnow.anggota.AnggotaActivity;
import com.business.nation.dprnow.naskah_akademik.NaskahActivity;
import com.business.nation.dprnow.regulasi.RegulasiActivity;
import com.business.nation.dprnow.util.SessionManager;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class FragmentInformasi extends Fragment {

    CardView cardAnggota, cardNaskah, cardRegulasi, cardAKD, cardLogout;

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

        cardNaskah = view.findViewById(R.id.cardNaskah);

        cardNaskah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NaskahActivity.class);

                ((v.getContext())).startActivity(intent);
            }
        });

        cardRegulasi = view.findViewById(R.id.cardRegulasi);

        cardRegulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegulasiActivity.class);

                ((v.getContext())).startActivity(intent);
            }
        });

        cardAKD = view.findViewById(R.id.cardAKD);

        cardAKD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AkdActivity.class);

                ((v.getContext())).startActivity(intent);
            }
        });

        cardLogout = view.findViewById(R.id.cardLogout);

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(v);
            }
        });
    }

    private void dialog(final View v) {
        new FancyAlertDialog.Builder(getActivity())
                .setTitle("Logout Akun ")
                .setBackgroundColor(Color.parseColor("#DC110E"))  //Don't pass R.color.colorvalue
                .setMessage("Apakah Anda Ingin Keluar Akun ?")
                .setNegativeBtnText("Batal")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ya")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_info_white, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        new SessionManager(getActivity()).logout();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                        Toast.makeText(getActivity(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                })
                .build();
    }


}
