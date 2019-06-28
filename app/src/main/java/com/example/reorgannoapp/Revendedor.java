package com.example.reorgannoapp;

public class Revendedor {

    String user_id;
    String revendedor_nome;
    String revendedor_telefone;

    boolean hidratante;

    double lat;
    double lon;

    public Revendedor(){

    }

    public Revendedor(String id, String nome, String telefone,double lat,double lon,boolean hidratante){
        this.user_id = id;
        this.revendedor_nome= nome;
        this.revendedor_telefone = telefone;
        this.lat=lat;
        this.lon = lon;
        this.hidratante = hidratante;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getRevendedor_nome() {
        return revendedor_nome;
    }

    public String getRevendedor_telefone() {
        return revendedor_telefone;
    }

    public boolean isHidratante() {
        return hidratante;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
