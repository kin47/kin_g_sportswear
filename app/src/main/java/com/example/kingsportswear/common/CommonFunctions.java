package com.example.kingsportswear.common;

import android.content.res.Resources;

import com.example.kingsportswear.R;

import java.util.List;

public class CommonFunctions {
    public static List<String> getClothesSize() {
        return List.of("XS", "S", "M", "L", "XL", "XXL", "3XL");
    }

    public static List<String> getShoesSize() {
        return List.of("36", "37", "38", "39", "40", "41", "42", "43", "44", "45");
    }

    public static String formatPrice(double price) {
        int priceInt = (int) price;
        String priceString = String.valueOf(priceInt);
        for(int i = priceString.length() - 3; i > 0; i -= 3) {
            priceString = priceString.substring(0, i) + "." + priceString.substring(i);
        }
        return priceString + " đ";
    }
}
