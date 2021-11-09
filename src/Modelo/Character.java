package Modelo;

import Modelo.Apariencia.ManagerApariencia;

import java.util.ArrayList;

public class Character extends Personaje {

    private ArrayList<Weapon> weapons;

    public Character(String nombre, ManagerApariencia apariencia, int vida, int ataque, int nivel, int campos,
                     int nivelAparicion, float costo, WeaponManager armas, ArrayList<Weapon> weapons) {
        super(nombre,apariencia, vida,ataque,nivel, campos, nivelAparicion, costo,  armas);
        this.weapons = weapons;
    }

    public Character(String nombre, ManagerApariencia apariencia, int vida, int ataque, int nivel, int campos,
                     int nivelAparicion, float costo, WeaponManager armas) {
        super(nombre,apariencia, vida,ataque,nivel, campos, nivelAparicion, costo,  armas);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setArmas(ArrayList<Weapon> weapons) {

        this.weapons = weapons;
    }

    public void rechargeWeapon(){
        // Recargar armas.
    }
}
