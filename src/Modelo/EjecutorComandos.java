package Modelo;

import java.io.IOException;

public class EjecutorComandos {

    //Las validaciones se hacen en pantalla
    boolean canExecute;

//    private void doubleAttack(Equipo objetivo, Guerrero guerrero, Arma weapon1, Guerrero guerrero2, Arma weapon2){
//        //Logger.addToLog("Ataque Doble");
//        //validateData();
//        attack(objetivo,guerrero,weapon1);
//        attack(objetivo,guerrero,weapon2);
//    }
//
//    private void doubleWeapon(Equipo equipo, Guerrero guerrero, Arma weapon1, Arma weapon2){
//        //Logger.addToLog("Ataque Doble Arma");
//        attack(equipo,guerrero,weapon1);
//        attack(equipo,guerrero,weapon2);
//    }
//
//    public void rechargeWeapon(Guerrero guerrero) throws IOException {
//        //Logger.addToLog("Realoding arma");
//        if (guerrero.canReload()) {
//            guerrero.reload();
//            Partida.getInstance().sendToClients("reloaded weapons");
//        }
//        else {
//            Partida.getInstance().sendToClients("Available weapons");
//        }
//    }

    public void mutualExit(Equipo equipo1,Equipo equipo2) throws IOException {//Tienen que estar los dos de acuerdo
        //Logger.addToLog("Mutual exit");
        Partida.getInstance().endGame();
        equipo1.giveUp();
        equipo2.giveUp();
        Partida.getInstance().sendToClients("Both players gave up");
    }

    public void giveUp(Equipo equipo) throws IOException {
        //Logger.addToLog("Surrender");
        Partida.getInstance().endGame();
        equipo.giveUp();
        Partida.getInstance().sendToClients(equipo.getUsuario().getNombre() +" gave up");
    }

    public void passTurn(Equipo equipo) throws IOException {
        //Logger.addToLog("Turn passed");
        Partida.getInstance().nextTurn();
        Partida.getInstance().sendToClients(equipo.getUsuario().getNombre() + " passed turn");
    }

//    public void attack(Equipo objetivo,Guerrero guerrero,Arma arma){
//        //Logger.addToLog("Attack")//Asociado al jugador actual
//        //Logger.addToLog(characterName + " atacked with "+weaponName)
//        for (Guerrero guerreroEnemigo : objetivo.getGuerreros()) {
//            guerreroEnemigo.recieveDamage(arma.getDano(guerreroEnemigo.getType()));
//        }
//    }

    public boolean canExecute() {
        return canExecute;
    }

    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }
}
