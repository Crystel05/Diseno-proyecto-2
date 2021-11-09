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

    public boolean canReload(){
        for (Arma arma: armas){
            if(arma.isActive())
                return false;
        }
        return true;
    }

    public TiposE getType() {
        return type;
    }

    public void recieveDamage(int dano) {
        //Logger.addToLog(name+" recieved " +dano+"% damage");
        this.health = this.health-damage;
        if(health <= 0)
            //Logger.addToLog("and died");
    }

    public void reload() {
        for (Arma arma:armas) {
            arma.setActive(true);
        }
    }

    public Arma getArma(String armaString) {
        for (Arma arma:armas){
            if(arma.name.equals(armaString))
                return arma;
        }
        return null;
    }
}
