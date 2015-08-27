package com.gdgssu.android_deviewsched.helper;

import android.content.Context;
import android.util.TypedValue;

public class DPtoPixelConvertHelper {
    public static int dpToPiexl(int dp, Context context){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());

        return px;
    }
}
