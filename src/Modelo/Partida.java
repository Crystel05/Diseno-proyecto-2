package Modelo;

import CommandPattern.AttackCommand;
import CommandPattern.Chat;
import CommandPattern.CommandManager;
import CommandPattern.Enumerable.CommandsE;
import FileManager.Logger;
import Model.*;
import Model.Character;
import Network.BaseServerClasses.BasicServerClient;
import Network.Server.Server;
import ProjectNetwork.CommandRequestHandler;
import ProjectNetwork.CommandServerSideClient;
import ProjectNetwork.Responses.AvaliableWariorsResponse;
import ProjectNetwork.Responses.MessageResponse;
import Utils.JsonLoader;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Partida extends Server{

    private static Partida partida;
    public int PERSONAJES_POR_JUGADOR = 5;

    private EjecutorComandos ejecutarComandos;

    public Equipo[] equipos = new Equipo[2];
    //Referencia al prototypeManager de la libreria de proyecto 1
    private Personaje[] personajes;//Esto es temporal mientras se implementa el proyecto1
    private Arma[] armas;
    private int mutualExit;
    private int inTurn,notInTurn;
    //para leer json personajes
    private JsonLoader json;
    private ICreator factory1 ;
    private ICreator factory2 ;


    private Partida(int port,CommandRequestHandler requestHandler) throws IOException, ClassNotFoundException {
        super(port,requestHandler);
        ComodinTimer comodinTimer = new ComodinTimer();
        crearArmas();
        crearPersonajes();
        comodinTimer.start();
        factory1 = new WeaponPrototypeFactory();
        factory2 = new CharacterPrototypeFactory();
        cargarFactorys();


    }

    public void setGuerrerosDisponibles(Personaje[] personajes){
        this.personajes = personajes;

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
    public static void createInstance(int port,CommandRequestHandler requestHandler) throws IOException, ClassNotFoundException {
        partida = partida == null? new Partida(port,requestHandler):partida;
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

    public Equipo equipoEnemigo(){
        for (Equipo equipo: equipos){
            if(equipo.isInTurn())
                return  equipo;
        }
        return  null;
    }


    public void endGame() throws IOException {
        ejecutarComandos.setCanExecute(false);
        sendToClients("Game is over");
        //TODO: Guardar los tados de l usuario actualizado Actualizar los datos de los usuarios.
    }


    //Esto puede hacer la calidaciones y luego enviarlas a los metodos del ejecutor.

    public void attackCommand(String guerreroString,String armaString) throws IOException {
        if (ejecutarComandos.canExecute()){
            Personaje guerrero = equipos[inTurn].getGuerrero(guerreroString);
            Arma arma = guerrero.getArma(armaString);
            ejecutarComandos.attack(guerrero,arma);
            nextTurn();
        }
        else {
            //Ya termino el juego... YANO !!
        }
        updateUsuarios();
    }


    public void doubleAttack(String guerreroName1,String armaName1,String guerreroName2,String armaName2) throws IOException {
        if(getEquipoInTurn().isComodin()){
            Personaje guerrero1 = equipos[inTurn].getGuerrero(guerreroName1);
            Personaje guerrero2 = equipos[inTurn].getGuerrero(guerreroName2);
            Arma arma1 = guerrero1.getArma(armaName1);
            Arma arma2 = guerrero2.getArma(armaName2);
            ejecutarComandos.doubleAttack(guerrero1,arma1,guerrero2,arma2);
            nextTurn();
        }
        else {
            sendToClients("No disponible el comodin");
        }
        updateUsuarios();

    }

    public void doubleWeapon(String guerreroName,String armaName1,String armaName2) throws IOException {
        if(getEquipoInTurn().isComodin()){
            Personaje guerrero = equipos[inTurn].getGuerrero(guerreroName);
            Arma arma1 = guerrero.getArma(armaName1);
            Arma arma2 = guerrero.getArma(armaName2);
            ejecutarComandos.doubleWeapon(guerrero,arma1,arma2);
            nextTurn();//Pasa el turno solo
        }
        else {
            sendToClients("No disponible el comodin");
        }
        updateUsuarios();
    }

    public void giveUpCommand(String[] params) throws IOException {
        ejecutarComandos.giveUp();
        updateUsuarios();
    }

    public void mutualExitCommand() throws IOException {
        mutualExit +=1;
        if(mutualExit > 1)
            ejecutarComandos.mutualExit();
        updateUsuarios();
    }
    public void passTurnCommand() throws IOException {
        ejecutarComandos.passTurn();
        updateUsuarios();
    }
    public void chatCommand(String message) throws IOException {
        directMessageNotInTurn(message);
    }

    public void errorCommand(String param) throws IOException {
        Logger.addToLogger("Error de comando "+param);
        updateUsuarios();
    }

    public void rechargeCommand(String guerreroString) throws IOException {
        Personaje guerrero = equipos[inTurn].getGuerrero(guerreroString);//Se usa para buscar guerreros
        ejecutarComandos.rechargeWeapon(guerrero);
        updateUsuarios();
    }

    public void selectPlayerCommand() throws IOException {
        Logger.addToLogger("Jugador "+ inTurn +" Selecciono personaje");
        updateUsuarios();
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

    public void sendToClients(AttackInfo attackInfo) throws IOException {        int i = 0;
        for (BasicServerClient client:getClientes()){
            ((CommandServerSideClient)client).sendAttackInfo(attackInfo);
        }

    }

    public void comodinTimer(){
        for (Equipo equipo:equipos) {
            equipo.setComodin(true);
        }
    }



    public void crearPersonajes(){
        personajes = new Personaje[2];
        for (int index = 0; index < 2; index++){
            Character character = new Personaje();
            character.setLife(100);
            character.setHitsPerTime(10);
            character.setFieldsInArmy(10);
            character.setLevelRequired(1);
            character.setName("Personaje"+index);
            Personaje p = (Personaje) character;
            p.setTipo(EnumTipoPersonaje.AGUA);
            personajes[index] = p;
        }



    }

    public void crearArmas(){
        armas = new Arma[5];
        for (int i = 0;i < 5;i++){
            Weapon weapon = new Arma();
            weapon.setName("Test"+i);
            armas[i] = (Arma) weapon;
        }
    }

    public Arma[] getArmasDisponibles() {
        return armas;
    }


    //Traer armas y personajes guardados y guardarlos aca.
    public void cargarFactorys() throws IOException {
        json = JsonLoader.getInstance();  //Lee y guarda personajes y armas
        //Se guardan en los factorys respectivos
        json.getWeapons().forEach(it -> {
            factory1.addPrototype(it.getName(), it);
        });
        json.getCharacters().forEach(it -> {
            factory2.addPrototype(it.getName(), it);
        });

    }

    public HashMap<String, IPrototype> cargarArmas(){
        return factory1.getPrototypes();

    }

    public HashMap<String, IPrototype> cargarPersonajes(){
        return factory2.getPrototypes();

    }

}
