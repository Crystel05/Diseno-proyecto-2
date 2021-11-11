package Modelo;

import FileManager.Logger;

import java.io.IOException;

public class EjecutorComandos {

    //Las validaciones se hacen en pantalla
    boolean canExecute;

    private void doubleAttack(Equipo objetivo, Personaje guerrero, Arma weapon1, Personaje guerrero2, Arma weapon2){
        //Logger.addToLog("Ataque Doble");
        attack(objetivo,guerrero,weapon1);
        attack(objetivo,guerrero,weapon2);
    }

    private void doubleWeapon(Equipo equipo, Personaje guerrero, Arma weapon1, Arma weapon2){
        //Logger.addToLog("Ataque Doble Arma");
        attack(equipo,guerrero,weapon1);
        attack(equipo,guerrero,weapon2);
    }

    public void rechargeWeapon(Personaje guerrero) throws IOException {
        Logger.addToLogger("Recargando arma");
        if (guerrero.canReload()) {
            guerrero.reload();
            Logger.addToLogger("Armas recargadas");
            Partida.getInstance().directMessageInTurn("Armas recargadas");
        }
        else {
            Logger.addToLogger("No se puede recargar armas disponibles");
            Partida.getInstance().directMessageInTurn("No se puede recargar armas disponibles");
        }
    }

    public void mutualExit(Equipo[] equipos) throws IOException {//Tienen que estar los dos de acuerdo
        Logger.addToLogger("Salida mutua");
        Partida.getInstance().endGame();
        equipos[0].giveUp();
        equipos[1].giveUp();
        String mensaje = "Se rinden ambos jugadores";
        Logger.addToLogger(mensaje);
        Partida.getInstance().sendToClients(mensaje);
    }

    public void giveUp(Equipo equipo) throws IOException {
        Logger.addToLogger("Rendirse");
        Partida.getInstance().endGame();
        equipo.giveUp();
        String mensaje = equipo.getUsuario().getNombre() +" gave up";
        Logger.addToLogger(mensaje);
        Partida.getInstance().sendToClients(mensaje);
    }

    public void passTurn(Equipo equipo) throws IOException {
        Logger.addToLogger("Turn passed");
        Partida.getInstance().nextTurn();
        Partida.getInstance().sendToClients(equipo.getUsuario().getNombre() + " passed turn");
    }

    public void attack(Equipo objetivo,Personaje guerrero,Arma arma){//Se puede construir un mensaje para enviar.
        Logger.addToLogger("Attack");//Asociado al jugador actual
        Logger.addToLogger(guerrero.getName() + " ataca con "+arma.getName());
        //Agregar al personaje cuando es una ataque exitoso o fallido
        for (Personaje guerreroEnemigo : objetivo.getGuerreros()) {
            int dano = arma.getDano(guerreroEnemigo.getType());
            guerreroEnemigo.recieveDamage(dano);
            Logger.addToLogger("Haciendo "+dano+ " de dano");
            if(guerrero.isAlive())
                System.out.println("Ataque fallido");//Esto agrega al usuario
            else
                System.out.println("Ataque exitoso");
        }
    }

    public boolean canExecute() {
        return canExecute;
    }

    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }
}
