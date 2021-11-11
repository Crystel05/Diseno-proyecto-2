package Modelo;

import FileManager.Logger;
import Network.BaseServerClasses.BasicServerClient;
import Network.Server.Server;
import ProjectNetwork.CommandRequestHandler;
import ProjectNetwork.CommandServerSideClient;
import ProjectNetwork.Responses.AvaliableWariorsResponse;
import ProjectNetwork.Responses.MessageResponse;
import java.io.IOException;

public class Partida extends Server{

    private static Partida partida;

    private EjecutorComandos ejecutarComandos;

    public Equipo[] equipos = new Equipo[2];
    //Referencia al prototypeManager de la libreria de proyecto 1
    private Personaje[] personajes;//Esto es temporal mientras se implementa el proyecto1
    private int mutualExit;
    private int inTurn,notInTurn;


    private Partida(int port,CommandRequestHandler requestHandler) throws IOException, ClassNotFoundException {
        super(port,requestHandler);
    }

    public void addEquipo(Equipo equipo) {
        if(equipos[0] == null)
            this.equipos[0] = equipo;
        this.equipos[1] = equipo;
    }

    public Equipo[] getEquipos(){
        return  equipos;
    }

    //xd
    public static Partida getInstance(){
        return partida;
    }

    //Creacion del Singleton
    public static Partida createInstance(int port,CommandRequestHandler requestHandler) throws IOException, ClassNotFoundException {
        return partida == null? new Partida(port,requestHandler):partida;
    }

    public void nextTurn(){
        equipos[inTurn].setInTurn(false);
        inTurn += 1;
        if(inTurn != 1)
            inTurn = 0;
        equipos[inTurn].setInTurn(true);
    }

    public Equipo getEquipoInTurn() {
        return equipos[inTurn];
    }

    private boolean weaponEnabled(Arma weapon){
        return weapon.isActive();
    }

    private Equipo equipoEnemigo(){
        for (Equipo equipo: equipos){
            if(equipo.isInTurn())
                return  equipo;
        }
        return  null;
    }


    public void endGame() throws IOException {
        ejecutarComandos.setCanExecute(false);
        sendToClients("Game is over");
    }


    //Esto puede hacer la calidaciones y luego enviarlas a los metodos del ejecutor.

    public void attackCommand(String guerreroString,String armaString) throws IOException {
        if (ejecutarComandos.canExecute()){
            Personaje guerrero = equipos[inTurn].getGuerrero(guerreroString);
            Arma arma = guerrero.getArma(armaString);
            ejecutarComandos.attack(equipoEnemigo(),guerrero,arma);
            updateUsuarios();
        }
        else {
            //Ya termino el juego...
        }
    }


    public void useWildCard(String[] params){

        //convierte los parametros en los objetos
        //valida los objetos
        //luego se los envia al ejecutor de comandos

    }

    public void giveUpCommand(String[] params){

        //convierte los parametros en los objetos
        //valida los objetos
        //luego se los envia al ejecutor de comandos

    }
    public void mutualExitCommand() throws IOException {
        mutualExit +=1;
        if(mutualExit > 1)
            ejecutarComandos.mutualExit(equipos);
    }
    public void passTurnCommand() throws IOException {
        ejecutarComandos.passTurn(equipos[inTurn]);
    }
    public void chatCommand(){
        //For clientes mande un mensaje directo a todos los que no hayan hecho el request.
    }

    public void errorCommand(String param){
        Logger.addToLogger("Error de comando "+param);
    }

    public void rechargeCommand(String guerreroString) throws IOException {
        //Validar si existe el guerrero o se hace en pantalla? Se hacen las validadciones en la pantalla.
        Personaje guerrero = equipos[inTurn].getGuerrero(guerreroString);//Se usa para buscar guerreros
        ejecutarComandos.rechargeWeapon(guerrero);
    }

    //Responses enviados por el servidor
    public void updateUsuarios() throws IOException {
        int i = 0;
        for (BasicServerClient client:getClientes()){
            ((CommandServerSideClient)client).updateData(equipos[i++]);
        }
    }

    public void sendToClients(String string) throws IOException {
        int i = 0;
        for (BasicServerClient client:getClientes()){
            ((CommandServerSideClient)client).sendMessage(string);
        }
    }

    public void directMessageInTurn(String string) throws  IOException{
        CommandServerSideClient client = (CommandServerSideClient)getClientes().get(0);
        MessageResponse response = new MessageResponse(string);
        if(client.getEquipo().isInTurn())
            client.getResponseSender().sendResponse(response);
        else
            getClientes().get(1).getResponseSender().sendResponse(response);
    }

    public void directMessageNotInTurn(String string) throws  IOException{
        CommandServerSideClient client = (CommandServerSideClient)getClientes().get(0);
        MessageResponse response = new MessageResponse(string);
        if(!client.getEquipo().isInTurn())
            client.getResponseSender().sendResponse(response);
        else
            getClientes().get(1).getResponseSender().sendResponse(response);
    }


    public Personaje[] getPersonajesDisponibles() {
        return personajes;
    }
}
