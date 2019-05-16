package com.example.reorgannoapp;

public class Revendedor {

    String revendedor_id;
    String revendedor_nome;
    String revendedor_telefone;

    public Revendedor(){

    }

    public Revendedor(String id, String nome, String telefone){
        this.revendedor_id = id;
        this.revendedor_nome= nome;
        this.revendedor_telefone = telefone;
    }

    public String getRevendedor_id() {
        return revendedor_id;
    }

    public String getRevendedor_nome() {
        return revendedor_nome;
    }

    public String getRevendedor_telefone() {
        return revendedor_telefone;
    }
}
