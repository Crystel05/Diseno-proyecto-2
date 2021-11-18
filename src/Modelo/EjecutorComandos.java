package Modelo;

import FileManager.Logger;

import java.io.IOException;

public class EjecutorComandos {

    //Las validaciones se hacen en pantalla
    boolean canExecute;

    public void doubleAttack( Personaje guerrero, Arma weapon1, Personaje guerrero2, Arma weapon2) throws IOException {
        Logger.addToLogger("Ataque Doble");
        attack(guerrero,weapon1);
        attack(guerrero,weapon2);
    }

    public void doubleWeapon(Personaje guerrero, Arma weapon1, Arma weapon2) throws IOException {
        Logger.addToLogger("Ataque Doble Arma");
        attack(guerrero,weapon1);
        attack(guerrero,weapon2);
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

    public void mutualExit() throws IOException {//Tienen que estar los dos de acuerdo
        Logger.addToLogger("Salida mutua");
        Partida.getInstance().endGame();
        Partida.getInstance().getEquipoInTurn().giveUp();
        Partida.getInstance().equipoEnemigo().giveUp();
        String mensaje = "Se rinden ambos jugadores";
        Logger.addToLogger(mensaje);
        Partida.getInstance().getEquipoInTurn().getUsuario().addRendiciones();
        Partida.getInstance().equipoEnemigo().getUsuario().addRendiciones();
        Partida.getInstance().sendToClients(mensaje);
    }

    public void giveUp() throws IOException {
        Logger.addToLogger("Rendirse");
        Partida.getInstance().endGame();
        Partida.getInstance().getEquipoInTurn().giveUp();
        String mensaje = Partida.getInstance().getEquipoInTurn().getUsuario().getNombre() +" gave up";
        Logger.addToLogger(mensaje);
        Partida.getInstance().getEquipoInTurn().getUsuario().addRendiciones();
        Partida.getInstance().equipoEnemigo().getUsuario().addVictorias();
        Partida.getInstance().sendToClients(mensaje);
    }

    public void passTurn() throws IOException {
        Logger.addToLogger("Turn passed");
        Partida.getInstance().nextTurn();
        Partida.getInstance().sendToClients(Partida.getInstance().getEquipoInTurn().getUsuario().getNombre() + " passed turn");
    }

    public void attack(Personaje guerrero,Arma arma) throws IOException {//Se puede construir un mensaje para enviar.
        Logger.addToLogger("Attack");//Asociado al jugador actual
        Logger.addToLogger(guerrero.getName() + " ataca con "+arma.getName());
        AttackInfo attackInfo = new AttackInfo(guerrero.getName(),arma.getName());
        //Agregar al personaje cuando es una ataque exitoso o fallido
        int muertos = 0;
        int sumaDano = 0;
        for (Personaje guerreroEnemigo : Partida.getInstance().equipoEnemigo().getGuerreros()) {
            if(guerreroEnemigo.isAlive()){
                int dano = arma.getDano(guerreroEnemigo.getType());
                guerreroEnemigo.recieveDamage(dano);
                sumaDano += dano;
                attackInfo.addToDamageToTeam(guerreroEnemigo.getName(),dano);
                if(!guerreroEnemigo.isAlive()){
                    muertos++;
                }
                Logger.addToLogger("Haciendo "+dano+ " de dano a"+guerreroEnemigo.getName());
                if(sumaDano <= 100){
                    Partida.getInstance().getEquipoInTurn().getUsuario().addAtaquesFallidos();
                }
                else {
                    Partida.getInstance().getEquipoInTurn().getUsuario().addAtaquesExitosos();
                }
            }
            else {
                muertos++;
            }
        }
        arma.setActive(false);
        guerrero.emptyWeapons();//QUITAR ESTA LINEA
        if(muertos == Partida.getInstance().PERSONAJES_POR_JUGADOR){
            System.out.println("Ya se mueren todos");
            Partida.getInstance().getEquipoInTurn().getUsuario().addVictorias();
            Partida.getInstance().equipoEnemigo().getUsuario().addDerrotas();
            Partida.getInstance().endGame();
        }
        Partida.getInstance().directMessageNotInTurn(attackInfo);
        Partida.getInstance().directMessageInTurn(sumaDano);
    }


    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }
}
