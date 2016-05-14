package com.secretbiology.managesmart.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {

    public String timeStamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a d MMM yy", Locale.getDefault());
        return formatter.format(new Date());
    }

    public String date(){
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yy", Locale.getDefault());
        return formatter.format(new Date());
    }

    public String time(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm", Locale.getDefault());
        return formatter.format(new Date());
    }
}
