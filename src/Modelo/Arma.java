package Modelo;

import Model.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Arma extends Weapon implements Serializable {

    private boolean isActive;
    private ArrayList<EnumTipoPersonaje> armaAtaca;
    private ArrayList <Integer> daños;

    public Arma(int scope, double damage, double explotionRange, boolean levelIncrease, String name, HashMap<Integer, ArrayList<String>> aspect,
                int level, double cost, boolean isActive, ArrayList<EnumTipoPersonaje> armas) {

        super(scope, damage, explotionRange, levelIncrease, name, aspect, level, cost);  // super(name, aspect, level, cost); Esto lo hace la mamá gameEntity, en Weapon
        this.isActive = isActive;
        this.armaAtaca = armas;
        generarDanos();
    }
    public Arma(int scope, double damage, double explotionRange, boolean levelIncrease, String name, HashMap<Integer, ArrayList<String>> aspect,
                int level, double cost, boolean isActive, ArrayList<EnumTipoPersonaje> armas, ArrayList<Integer> daños) {

        super(scope, damage, explotionRange, levelIncrease, name, aspect, level, cost);  // super(name, aspect, level, cost); Esto lo hace la mamá gameEntity, en Weapon
        this.isActive = isActive;
        this.armaAtaca = armas;
        this.daños = daños;
        generarDanos();//Quitar si no se generar aleatoriamente.
    }

    private void generarDanos() {
        daños = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            daños.add(ThreadLocalRandom.current().nextInt(0, 25));
        }
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

    public int getDano(EnumTipoPersonaje tipoPersonaje){
        return daños.get(armaAtaca.indexOf(tipoPersonaje));
    }

    public Arma clone() {
        HashMap<Integer, ArrayList<String>> aspect = this.aspect;
        return new Arma(this.getScope(),this.getDamage(),this.getExplotionRange(),this.isLevelIncrease(),this.getName(),this.getAspect(),
                this.getLevel(),this.getCost(),this.isActive,this.getArmaAtaca(),this.getDaños());
    }

    public Arma deepClone() {
        return this.clone();
    }


}
