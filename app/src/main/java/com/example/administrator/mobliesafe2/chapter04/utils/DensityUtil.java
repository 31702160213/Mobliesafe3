package com.example.administrator.mobliesafe2.chapter04.utils;

import android.content.Context;

public class DensityUtil {
    /**
     * dip 转换像素 px
     */
    public static int dip2px(Context context,float dpValue){
        try {
          final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue*scale+0.5f);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int) dpValue;

    }
    /**
     *  像素 px 转换为 dip
     */
    public static int px2dip(Context context,float pxValue){
        try {
          final float scale = context.getResources().getDisplayMetrics().density;
          return (int) (pxValue/scale+0.5f);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int) pxValue;
    }
}
