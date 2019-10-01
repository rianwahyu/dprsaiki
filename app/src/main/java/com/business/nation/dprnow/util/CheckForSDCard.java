package com.business.nation.dprnow.util;

import android.os.Environment;

public class CheckForSDCard {
    public static boolean isSDCardPresent(){
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
