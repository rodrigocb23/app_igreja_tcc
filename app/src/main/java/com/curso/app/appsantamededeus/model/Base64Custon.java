package com.curso.app.appsantamededeus.model;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Custon {

   /* public static String encodeBase64(String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n | \\r)", "");
    }

    public static String decodeBase64(String texto){
        return new String(Base64.decode(texto, Base64.DEFAULT));
    }*/

    public static String encodeBase64(String s) {
        byte[] data = new byte[0];

        try {
            data = s.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            String base64Encoded = Base64.encodeToString(data, Base64.DEFAULT);

            return base64Encoded;

        }
    }

    public static String decodeBase64(String encoded) {
        byte[] dataDec = Base64.decode(encoded, Base64.DEFAULT);
        String decodedString = "";
        try {

            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } finally {

            return decodedString;
        }
    }
}
