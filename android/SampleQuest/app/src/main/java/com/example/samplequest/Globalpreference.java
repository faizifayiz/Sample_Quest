package com.example.samplequest;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class Globalpreference {

    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    private Context context;

    public Globalpreference(Context context){
        sharedPreference = context.getSharedPreferences("sample",MODE_PRIVATE);
        editor = sharedPreference.edit();

    }

    public void saveIp(String ipaddress){
        editor.putString("ip",ipaddress);
        editor.apply();
    }

    public String getIp(){ return sharedPreference.getString("ip","");}


    public void saveID(String id){
        editor.putString("id",id);
        editor.apply();
    }

    public String getID(){ return sharedPreference.getString("id","");}
}
