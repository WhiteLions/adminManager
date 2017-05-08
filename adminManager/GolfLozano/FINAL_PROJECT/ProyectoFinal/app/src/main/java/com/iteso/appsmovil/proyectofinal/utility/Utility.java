package com.iteso.appsmovil.proyectofinal.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.iteso.appsmovil.proyectofinal.LoginActivity;
import com.owlike.genson.Genson;

/**
 * Created by dideleo on 06/05/2015.
 */
public final class Utility {

    private static final Genson gensonInst = new Genson();

    public static final String MY_SETTINGS = "mySettings";

    public static final int MY_NOTIFICATIONS_FRAGMENT = 0;

    public static final int NEW_NOTIFICATION_FRAGMENT = 1;

    public static final int MONTHLY_INCOME_REPORT = 2;

    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }

    public static Genson getGensonInst(){
        return gensonInst;
    }

    public static final void doLogout(Context context){

        Utility.removeLoginState(context);

    }

    private static final void removeLoginState(Context context){

        SharedPreferences settings = context.getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("username");
        editor.remove("authorized");
        editor.commit();

    }



}
