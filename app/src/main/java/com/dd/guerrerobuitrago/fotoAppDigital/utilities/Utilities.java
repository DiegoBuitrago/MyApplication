package com.dd.guerrerobuitrago.fotoAppDigital.utilities;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Utilities {
    public String stringToBase64(String st) {
        byte[] data;
        try {
            data = st.getBytes("UTF-8");
            return Base64.encodeToString(data, Base64.DEFAULT);
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }

    public String base64ToString(String base64){
        byte[] data;
        try{
            data = Base64.decode(base64, Base64.DEFAULT);
            return new String(data, "UTF-8");
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
}