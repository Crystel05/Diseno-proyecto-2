package Modelo;

import java.util.ArrayList;

public class Arma {

    private boolean isActive;
    private ArrayList<TiposE> armas;
    private ArrayList <Integer> daños;

    public Arma(boolean isActive, ArrayList<TiposE> armas, ArrayList<Integer> daños) {
        this.isActive = isActive;
        this.armas = armas;
        this.daños = daños;
    }

    public Arma() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<TiposE> getArmas() {
        return armas;
    }

    public void setArmas(ArrayList<TiposE> armas) {
        this.armas = armas;
    }

    public ArrayList<Integer> getDaños() {
        return daños;
    }

    public void setDaños(ArrayList<Integer> daños) {
        this.daños = daños;
    }
}
