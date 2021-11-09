package Modelo;

public class EjecutorComandos {

    //Las validaciones se hacen en pantalla

    private void doubleAttack(Guerrero guerrero, Arma weapon1, Guerrero guerrero2, Arma weapon2){
        //Logger.addToLog("Ataque Doble");
        //validateData();
        attack(equipoEnemigo(),guerrero,weapon1);
        attack(equipoEnemigo(),guerrero,weapon2);
    }

    private void doubleWeapon(Guerrero guerrero, Arma weapon1, Arma weapon2){
        //Logger.addToLog("Ataque Doble Arma");
        attack(guerrero,weapon1);
        attack(guerrero,weapon2);
    }

    public void rechargeWeapon(Guerrero guerrero){
        //Logger.addToLog("Realoding arma");
        boolean canReload = true;
        for (Arma arma: guerrero.getArmas()){
            if(arma.isActive())
                canReload = false;
        }
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

    public void attack(Guerrero guerrero,Arma arma){
        //Logger.addToLog("Attack")//Asociado al jugador actual
        //Logger.addToLog(characterName + " atacked with "+weaponName)
        dealDamage(arma);
        nextTurn();
    }

}
