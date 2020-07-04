package com.curso.app.appsantamededeus.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference database;
    private static FirebaseAuth auth;

    //Retornar a instância do FirebaseDatabase
    public static DatabaseReference getDatabase(){
        if( database == null){
            database = FirebaseDatabase.getInstance().getReference();
        }

        return database;
    }

    //Retornar a instância do FirebaseAuth
    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return  auth;
    }
}
