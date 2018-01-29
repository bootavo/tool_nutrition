package gtf.nutricion.test.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bootavo on 6/09/2017.
 */

public class ClientSharedPreferences {

    private String PREFS_NAME = "midoc";
    //private String PREFS_KEY = "key";

    //midoc_id
    //midoc_user
    //midoc_name
    //midoc_degree
    //midoc_specialist

    public void saveString(Context ctx, String key, String value){
        SharedPreferences prefs =ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getValueString(Context ctx, String key){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String value = prefs.getString(key, null);
        return value;
    }

    public void saveInt(Context ctx, String key, int value){
        SharedPreferences prefs =ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getValueInt(Context ctx, String key){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int value = prefs.getInt(key, 0);
        return value;
    }

    public void saveBoolean(Context ctx, String key, boolean value){
        SharedPreferences prefs =ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(Context ctx, String key){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean value = prefs.getBoolean(key, false);
        return value;
    }

    public void clearSharedPreference(Context ctx) {
        SharedPreferences prefs =ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        //editor.apply();
    }

}
