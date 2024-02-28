package com.example.kingsportswear.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;

public class LoadingUtils {
    private static Dialog dialog;

    public static void showLoading(Context context) {
        Window window = dialog.getWindow();
        window.getAttributes().gravity = Gravity.CENTER;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setTitle(null);
        dialog.show();
    }

    public static void hideLoading() {
        dialog.dismiss();
    }
}
