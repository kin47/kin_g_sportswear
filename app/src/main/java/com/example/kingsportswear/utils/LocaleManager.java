package com.example.kingsportswear.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public class LocaleManager {

    private Context context;

     public LocaleManager(Context context) {
        this.context = context;
    }

    public static LocaleManager of(Context context) {
        return new LocaleManager(context);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Context updateResourceConfiguration(Locale locale) {
        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(locale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        return context.createConfigurationContext(config);
    }

    public Context updateResourceConfigurationLegacy(Locale locale) {
        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        return context;
    }

    public Context updateBaseContextLocale(Locale locale) {
        Locale.setDefault(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourceConfiguration(locale);
        } else {
            return updateResourceConfigurationLegacy(locale);
        }
    }

    public Context updateBaseContextLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        return updateBaseContextLocale(locale);
    }
}