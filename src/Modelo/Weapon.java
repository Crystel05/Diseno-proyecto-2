package Modelo;

import Modelo.Apariencia.ManagerApariencia;

import java.util.ArrayList;

public class Weapon extends Arma {

    private boolean isActive;
    private ArrayList<TiposE> armas;
    private ArrayList <Integer> daños;

    public Weapon(String nombre, int alcance, int dano, int nivel, int rangoExplosion, ManagerApariencia apariencia,
                  boolean isActive, ArrayList<TiposE> armas, ArrayList<Integer> daños) {
        super(nombre, alcance,dano, nivel,rangoExplosion,apariencia);
        this.isActive = isActive;
        this.armas = armas;
        this.daños = daños;
    }

    public Weapon(String nombre, int alcance, int dano, int nivel, int rangoExplosion, ManagerApariencia apariencia) {
        super(nombre,alcance,dano,nivel,rangoExplosion,apariencia);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<TiposE> getWeapons() {
        return armas;
    }

    public void setWeapons(ArrayList<TiposE> armas) {
        this.armas = armas;
    }

    public ArrayList<Integer> getDaños() {
        return daños;
    }

    public void setDaños(ArrayList<Integer> daños) {
        this.daños = daños;
    }
}
