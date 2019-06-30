package com.example.reorgannoapp;

public class Revendedor {


    String revendedor_nome;
    String revendedor_telefone;

    boolean hidratante;

    double lat;

    double lon;
    public Revendedor(){
        revendedor_nome = "";
        revendedor_telefone = "";
        lat = 0;
        lon = 0;
        hidratante = false;
    }

    public Revendedor(String nome, String telefone,double lat,double lon,boolean hidratante){
       this.revendedor_nome= nome;
        this.revendedor_telefone = telefone;
        this.lat=lat;
        this.lon = lon;
        this.hidratante = hidratante;
    }




    public String getRevendedor_nome() {
        return revendedor_nome;
    }

    public String getRevendedor_telefone() {
        return revendedor_telefone;
    }

    public boolean getHidratante() {
        return hidratante;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setRevendedor_nome(String revendedor_nome) {
        this.revendedor_nome = revendedor_nome;
    }

    public void setRevendedor_telefone(String revendedor_telefone) {
        this.revendedor_telefone = revendedor_telefone;
    }

    public void setHidratante(boolean hidratante) {
        this.hidratante = hidratante;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
