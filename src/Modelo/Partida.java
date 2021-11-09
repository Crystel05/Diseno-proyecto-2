package Modelo;

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
    private boolean canExecute;

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

    private void nextTurn(){
        if(equipoInTurn == equipos[0])
            equipoInTurn = equipos[1];
        else
            equipoInTurn = equipos[0];
    }

    public Equipo getEquipoInTurn() {
        return equipoInTurn;
    }

    private boolean isInTurn(Equipo equipo){
        return  equipo == equipoInTurn;
    }

    private boolean weaponEnabled(Arma weapon){
        return weapon.isActive();
    }

    private Equipo equipoEnemigo(){
        for (Equipo equipo: equipos){
            if(equipo != equipoInTurn)
                return  equipo;
        }
        return  null;
    }

    private void endGame() {
        ejecutarComandos.setCanExecute(false);
        sendToClients("Game is over");
    }


    //Esto puede hacer la calidaciones y luego enviarlas a los metodos del ejecutor.

    public attackCommand(String[] params){
        //convierte los parametros en los objetos
        //valida los objetos
        //luego se los envia al ejecutor de comandos
    }

    public comprobar(){
        //Antes de cualquier comando ve si puede ejecutar comandos.

        //Despues de cada comando ctualizar a los jugadores con el equipo.
    }



}
