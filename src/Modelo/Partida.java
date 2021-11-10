package Modelo;

import CommandPattern.Attack;
import Model.Weapon;

import java.util.ArrayList;

public class Partida {
    private static Partida partida;

    private Equipo equipo1;
    private Equipo equipo2;
    private EjecutorComandos ejecutarComandos;

    public Equipo[] equipos = new Equipo[2];
    private Equipo equipoInTurn;
    private boolean mutualGiveUp;
    private boolean winCondition;

    public Partida(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }


    //
    private void nextTurn(){
        if(equipoInTurn == equipos[0])
            equipoInTurn = equipos[1];
        else
            equipoInTurn = equipos[0];
    }

    public Equipo getEquipoInTurn() {
        return equipoInTurn;
    }

    public String attack(){
        return "";
    }

    private String doubleAttack(Personaje guerrero, Arma arma1, Personaje guerrero2, Arma arma2){
        String msg = "";
        Attack attack = new Attack(guerrero, arma1);
        Attack attack2 = new Attack(guerrero2, arma2);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(guerrero);
        objects.add(arma1);
        objects.add(guerrero2);
        objects.add(arma2);
        attack.execute(objects);
        attack2.execute(objects);
//        msg+= attack(equipo,arma1);//Cuidado aca porque si no existe la segunda arma ya se va a haber realizado el primer ataque
//        msg += "\n";
//        msg+= attack(equipo2,arma2);

        return  msg;
    }

    private String doubleWeapon(Personaje guerrero, Arma arma1, Arma arma2){
        String msg = "";
        Attack attack = new Attack(guerrero, arma1);
        Attack attack2 = new Attack(guerrero, arma2);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(guerrero);
        objects.add(arma1);
        objects.add(arma2);

        attack.execute(objects);
        attack2.execute(objects);
//        msg+= attack(character,arma1);
//        msg+="\n";
//        msg+= attack(character,arma2);
        return msg;
    }

    private boolean isInTurn(Equipo equipo){
        return  equipo == equipoInTurn;
    }

    private boolean weaponEnabled(Arma arma){
        return arma.isActive();
    }


    public String rechargeWeapon(Personaje guerrero){
        boolean canReload = true;
        for (Weapon arma: guerrero.getArmas()){
            if(((Arma)arma).isActive())
                canReload = false;
        }
        if (canReload) {
            guerrero.rechargeWeapon();
            return "reloaded weapons";
        }
        else {
            return  "Available weapons";
        }
    }

    private Equipo equipoEnemigo(){
        for (Equipo equipo: equipos){
            if(equipo != equipoInTurn)
                return  equipo;
        }
        return  null;
    }

    public String mutualExit(){//Tienen que estar los dos de acuerdo
        winCondition = true;
        equipoInTurn.giveUp();
        equipoEnemigo().giveUp();
        return  "Both players gave up";
    }



    public String giveUp(){
        winCondition = true;
        equipoInTurn.giveUp();
        endGame();
        return equipoInTurn.getUsuario().getNombre() +" gave up";
    }

    // TODO: Ver como pasar los parámetros
    public String useWildCard(String[] args){//Si se reciben dos armas o si s reciben dos character
//        if(args.length == 3)
//            return doubleWeapon(args[0],args[1],args[2]);
//        else
//            return doubleAttack(args[0],args[1],args[2],args[3]);
        return null;
    }

    public String passTurn(){
        String msg = equipoInTurn.getUsuario().getNombre() + " passed turn";
        nextTurn();
        return msg;
    }

    private void endGame() {
    }
}
