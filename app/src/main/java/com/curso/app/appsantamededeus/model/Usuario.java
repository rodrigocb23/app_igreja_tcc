package com.curso.app.appsantamededeus.model;

import com.curso.app.appsantamededeus.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String id) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public  void salvarUsuario(){
        DatabaseReference firebase = ConfiguracaoFirebase.getDatabase();
        DatabaseReference usuario = firebase.child("usuarios").child(getId());
        usuario.setValue(this);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
