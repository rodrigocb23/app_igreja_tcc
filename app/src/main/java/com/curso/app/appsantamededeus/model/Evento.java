package com.curso.app.appsantamededeus.model;

public class Evento {

    private String id;
    private String usuario;
    private String dataSalva;
    private String horaInicio;
    private String horaFim;
    private String decricao;
    private String local;

    public Evento(){
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataSalva() {
        return dataSalva;
    }

    public void setDataSalva(String dataSalva) {
        this.dataSalva = dataSalva;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }


    public void remover(){

    }
}
