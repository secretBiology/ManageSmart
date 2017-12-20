package com.secretbiology.managesmart.common;

import java.util.Locale;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

public class Helper {

    public static String getCurrencySymbol(String currencyCode) {
        java.util.Currency c = java.util.Currency.getInstance(currencyCode);
        return c.getSymbol(Locale.getDefault());
    }


}
