package Modelo;

import Model.Character;
import Model.Direction;
import Model.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class Personaje extends Character {

    public Personaje(int life, double hitsPerTime, double fieldsInArmy, int levelRequired, ArrayList<Weapon> weapons,
                     Weapon currentWeapon, Direction direction, String name, HashMap<Integer, ArrayList<String>> aspect,
                     int level, double cost) {
        super(life,hitsPerTime,fieldsInArmy, levelRequired, weapons, currentWeapon, direction, name, aspect, level, cost);
    }

    public Personaje() {

    }
    public void rechargeWeapon(){
        // Recargar armas.
    }

    public ArrayList<Weapon> getArmas(){
        return getWeapons();
    }
}
