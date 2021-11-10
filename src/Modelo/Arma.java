package Modelo;

import Model.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class Arma extends Weapon {

    private boolean isActive;
    private ArrayList<EnumTipoPersonaje> armaAtaca;
    private ArrayList <Integer> daños;

    public Arma(int scope, double damage, double explotionRange, boolean levelIncrease, String name, HashMap<Integer, ArrayList<String>> aspect,
                int level, double cost, boolean isActive, ArrayList<EnumTipoPersonaje> armas, ArrayList<Integer> daños) {

        super(scope, damage, explotionRange, levelIncrease, name, aspect, level, cost);  // super(name, aspect, level, cost); Esto lo hace la mamá gameEntity, en Weapon
        this.isActive = isActive;
        this.armaAtaca = armas;
        this.daños = daños;
    }

    public Arma() {}

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<EnumTipoPersonaje> getArmaAtaca() {
        return armaAtaca;
    }

    public void setArmaAtaca(ArrayList<EnumTipoPersonaje> armas) {
        this.armaAtaca = armas;
    }

    public ArrayList<Integer> getDaños() {
        return daños;
    }

    public void setDaños(ArrayList<Integer> daños) {
        this.daños = daños;
    }
}
