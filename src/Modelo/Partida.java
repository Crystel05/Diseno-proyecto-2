package Modelo;

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
    private Equipo equipoInTurn;
    //Referencia al prototypeManager de la libreria de proyecto 1
    //private Guerrero[] guerreros;//Esto es temporal mientras se implementa el proyecto1


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


    public void endGame() throws IOException {
        ejecutarComandos.setCanExecute(false);
        sendToClients("Game is over");
    }


    //Esto puede hacer la calidaciones y luego enviarlas a los metodos del ejecutor.

    public void attackCommand(String guerreroString,String armaString) throws IOException {
        //No olvidar las validaciones.
        if (ejecutarComandos.canExecute()){
            //Guerrero guerrero = getGuerreroEquipoEnTurno(guerreroString);
            //Arma arma = guerrero.getArma(armaString);
           // ejecutarComandos.attack(equipoEnemigo(),guerrero,arma);
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
    public void mutualExitCommand(String[] params){

        //convierte los parametros en los objetos
        //valida los objetos
        //luego se los envia al ejecutor de comandos
    }
    public void passTurnCommand(String[] params){

        //convierte los parametros en los objetos
        //valida los objetos
        //luego se los envia al ejecutor de comandos
    }
    public void chatCommand(){
        //For clientes mande un mensaje directo a todos los que no hayan hecho el request.
    }

    public void rechargeCommand(){

    }

    public void errorCommand(){

    }


//    private Guerrero getGuerreroEquipoEnTurno(String guerreroString) {
//        for (Guerrero guerrero:equipoInTurn.getGuerreros()) {
//            if(guerrero.name.equals(guerreroString));
//                return guerrero;
//        }
//        return null;
//    }



    //Responses enviados por el servidor
    public void updateUsuarios() throws IOException {
        int i = 0;
        for (BasicServerClient client:getClientes()){
            ((CommandServerSideClient)client).updateData(equipos[i++]);
        }
    }

//    public void enviarPersonajesDisponibles(int clientId) throws IOException {
//        ((CommandServerSideClient)getClientes().get(clientId)).sendResponse(new AvaliableWariorsResponse(guerreros));
//    }

    public void sendToClients(String string) throws IOException {
        int i = 0;
        for (BasicServerClient client:getClientes()){
            ((CommandServerSideClient)client).sendMessage(string);
        }
    }

    public void directMessage(String string,int clientId) throws  IOException{
        ((CommandServerSideClient)getClientes().get(clientId)).sendResponse(new MessageResponse(string));
    }



}
