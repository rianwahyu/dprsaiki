package com.business.nation.dprnow.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.business.nation.dprnow.R;


/**
 * Created by syahril on 11/4/17.
 */

public class DialogHelper {

    private static AlertDialog successdialog;
    private static AlertDialog staticdialog;
    private static AlertDialog noInternetdialog;

    public static AlertDialog loading(Context context, String message) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView textView = view.findViewById(R.id.textName);
        textView.setText(message);
        return new AlertDialog.Builder(context)
                .setView(view)
                .create();
    }

    public static AlertDialog YesNo(Context context, String message, String title, DialogInterface.OnClickListener dialogInterface) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ya", dialogInterface)
                .setNegativeButton("Tidak", null).setTitle(title).setMessage(message);
        return alertDialog.create();
    }

    public static AlertDialog YesNo(Context context, String message, String title, DialogInterface.OnClickListener dialogInterface, DialogInterface.OnClickListener dialogInterface1) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ya", dialogInterface)
                .setNegativeButton("Tidak", dialogInterface1).setTitle(title).setMessage(message);
        return alertDialog.create();
    }

    public static AlertDialog successDialog(Context context, String message, OnOk onOk) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_success, null);
        Button button = view.findViewById(R.id.button);
        TextView textView = view.findViewById(R.id.textMessage);
        textView.setText(message);
        successdialog = new AlertDialog.Builder(context)
                //.setMessage(message)
                .setCancelable(false)
                .setView(view)
                .create();

        button.setOnClickListener(onOk);

        return successdialog;
    }


    /*public static AlertDialog loadingWithProgress(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        TextView textView = view.findViewById(R.id.textName);
        textView.setText(message);
        return new AlertDialog.Builder(context)
                .setView(view)
                .create();
    }



    public static AlertDialog successDialogVoucher(Context context, String message, OnOk onOk) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_success_voucher, null);
        Button button = view.findViewById(R.id.button);
        TextView textView = view.findViewById(R.id.textMessage);
        textView.setText(message);
        successdialog = new AlertDialog.Builder(context)
                //.setMessage(message)
                .setCancelable(false)
                .setView(view)
                .create();

        button.setOnClickListener(onOk);

        return successdialog;
    }

    public static AlertDialog noInternet(Context context, String message, OnOk onOk) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_no_internet, null);
        Button button = view.findViewById(R.id.button);
        TextView textView = view.findViewById(R.id.textMessage);
        textView.setText("Mohon Periksa Kembali Koneksi Internet Anda, Aplikasi Ini Membutuhkan Koneksi Internet");
        successdialog = new AlertDialog.Builder(context)
                //.setMessage(message)
                .setCancelable(false)
                .setView(view)
                .create();

        button.setOnClickListener(onOk);

        return successdialog;
    }*/

    public static void notice(Context context, String message, DialogInterface.OnClickListener dialogInterface) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("Oke", dialogInterface)
                .setCancelable(false)
                .show();
    }

    public static void notice(Context context, String title, String message, DialogInterface.OnClickListener dialogInterface) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("Oke", dialogInterface)
                .setCancelable(false)
                .show();
    }

    public static AlertDialog.Builder showUpdateDialog(Context context, String version, String desc) {
        return new AlertDialog.Builder(context)
                .setTitle("Update versi " + version)
                .setMessage("Apa yang baru :\n" + desc);
    }

    public static void ImageDialog(String path, String desc, OnSimpan onSimpan) {

    }

    public abstract static class OnOk implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onOk(successdialog, view);
        }

        public abstract void onOk(AlertDialog alertDialog, View view);
    }

    public interface OnOkListener {
        void onOk(String value);
    }

    public interface OnSimpan {
        void onSimpan(String path, String desc);
    }
}
