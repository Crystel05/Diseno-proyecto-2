package Modelo;

import Model.Character;
import Model.Direction;
import Model.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Personaje extends Character implements Serializable {

    EnumTipoPersonaje tipo;

    public Personaje(int life, double hitsPerTime, double fieldsInArmy, int levelRequired, ArrayList<Weapon> weapons,
                     Weapon currentWeapon, Direction direction, String name, HashMap<Integer, ArrayList<String>> aspect,
                     int level, double cost) {
        super(life,hitsPerTime,fieldsInArmy, levelRequired, weapons, currentWeapon, direction, name, aspect, level, cost);
    }

    public Personaje (){

    }

    public ArrayList<Arma> getArmas(){
        ArrayList<Arma> armas = new ArrayList<>();
        for (Weapon weapon:getWeapons()){
            armas.add((Arma)weapon);
        };
        return armas;
    }

    public void reload() {
        for (Weapon weapon:getWeapons()) {
            Arma arma = (Arma) weapon;
            arma.setActive(true);
        }
    }

    public Arma getArma(String armaString) {
        for (Weapon arma:getWeapons()){
            if(arma.getName().equals(armaString)) {
                return (Arma) arma;
            }
        }
        return null;
    }

    public void recieveDamage(int damage){
        this.setLife(getLife()-damage);
    }

    public EnumTipoPersonaje getType() {
        return tipo;
    }

    public boolean isAlive() {
        return getLife() > 0;
    }

    public boolean canReload() {
        for (Weapon weapon:getWeapons()) {
            Arma arma = (Arma) weapon;
            if(arma.isActive())
                return false;
        }
        return true;
    }
    public boolean isArmaValida(String armaString){
        for (Weapon weapon:getWeapons()
        ) {
            if(weapon.getName().equals(armaString))
                return true;
        }
        return false;
    }

    public void setTipo(EnumTipoPersonaje tipo) {
        this.tipo = tipo;
    }
}
