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

    public TiposE getType() {
    }

    public void recieveDamage(int dano) {
        //Logger.addToLog(name+" recieved " +dano+"% damage");
        this.health = this.health-damage;
        if(health <= 0)
            //Logger.addToLog("and died");
    }
}
