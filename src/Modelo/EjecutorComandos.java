package Modelo;

public class EjecutorComandos {

    //Las validaciones se hacen en pantalla

    private void doubleAttack(Equipo objetivo, Guerrero guerrero, Arma weapon1, Guerrero guerrero2, Arma weapon2){
        //Logger.addToLog("Ataque Doble");
        //validateData();
        attack(objetivo,guerrero,weapon1);
        attack(objetivo,guerrero,weapon2);
    }

    private void doubleWeapon(Guerrero guerrero, Arma weapon1, Arma weapon2){
        //Logger.addToLog("Ataque Doble Arma");
        attack(guerrero,weapon1);
        attack(guerrero,weapon2);
    }

    public void rechargeWeapon(Guerrero guerrero,boolean canReload){
        //Logger.addToLog("Realoding arma");
        if (canReload) {
            guerrero.rechargeWeapon();
            sendToClients("reloaded weapons");
        }
        else {
            sendToClients("Available weapons");
        }
    }

    public void mutualExit(){//Tienen que estar los dos de acuerdo
        //Logger.addToLog("Mutual exit");
        winCondition = true;
        equipo1.giveUp();
        equipo2.giveUp();
        sendToClients("Both players gave up");
    }

    public void giveUp(){
        //Logger.addToLog("Surrender");
        winCondition = true;
        equipoInTurn.giveUp();
        endGame();
        sendToClients(equipoInTurn.getUsuario().getNombre() +" gave up");
    }

    public void passTurn(){
        //Logger.addToLog("Turn passed");
        nextTurn();
        sendToClients(equipoInTurn.getUsuario().getNombre() + " passed turn");
    }

    public void attack(Equipo objetivo,Arma arma){
        //Logger.addToLog("Attack")//Asociado al jugador actual
        //Logger.addToLog(characterName + " atacked with "+weaponName)
        for (Guerrero guerrero : objetivo.getGuerreros()) {
            guerrero.recieveDamage(arma.getDano(guerrero.getType()));
        }
        nextTurn();
    }

}
