package org.example;

public class Pedido {

    private double valorItens;
    private double distanciaKm;
    private double pesoKg;
    private boolean fragil;
    private boolean expresso;

    public Pedido(double valorItens, double distanciaKm, double pesoKg,
                  boolean fragil, boolean expresso) {
        this.valorItens = valorItens;
        this.distanciaKm = distanciaKm;
        this.pesoKg = pesoKg;
        this.fragil = fragil;
        this.expresso = expresso;
    }

    public double getValorItens() {
        return valorItens;
    }

    public void setValorItens(double valorItens) {
        this.valorItens = valorItens;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public boolean isExpresso() {
        return expresso;
    }

    public void setExpresso(boolean expresso) {
        this.expresso = expresso;
    }
}
