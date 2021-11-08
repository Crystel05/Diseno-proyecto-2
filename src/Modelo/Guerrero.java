package Modelo;

import java.util.ArrayList;

public class Guerrero {

    private ArrayList<Arma> armas;

    public Guerrero(ArrayList<Arma> armas) {
        this.armas = armas;
    }

    public Guerrero() {
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }

    public void setArmas(ArrayList<Arma> armas) {
        this.armas = armas;
    }

    public void rechargeWeapon(){
        // Recargar armas.
    }
}
