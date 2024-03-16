package com.example.kingsportswear.utils;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtils {
    private static AlertDialog dialog;

    public static void showInformationDialog(Context context, String title, String message, Runnable onTap) {
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    if (onTap != null) {
                        onTap.run();
                    }
                })
                .show();
    }

    public static void showActionDialog(Context context, String title, String message, String positiveButtonText, String negativeButtonText, Runnable onPositiveTap, Runnable onNegativeTap) {
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, (dialog, which) -> {
                    dialog.dismiss();
                    if (onPositiveTap != null) {
                        onPositiveTap.run();
                    }
                })
                .setNegativeButton(negativeButtonText, (dialog, which) -> {
                    dialog.dismiss();
                    if (onNegativeTap != null) {
                        onNegativeTap.run();
                    }
                })
                .show();
    }
}
