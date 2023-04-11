package com.example.proj_pascoa;


public class Carro {
    private String placa;
    private int ano;
    private String cor;
    private String marcaModelo;
    
    public Carro(){

    }

    public Carro(String placa, int ano, String cor, String marMod){
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        marcaModelo = marMod;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public String getMarcaModelo() {
        return marcaModelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setMarcaModelo(String marcaModelo) {
        this.marcaModelo = marcaModelo;
    }

    
}
