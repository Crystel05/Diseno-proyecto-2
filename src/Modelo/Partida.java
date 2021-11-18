package Modelo;

import CommandPattern.AttackCommand;
import CommandPattern.Chat;
import CommandPattern.CommandManager;
import CommandPattern.Enumerable.CommandsE;
import FileManager.Logger;
import Model.*;
import Model.Character;
import Modelo.Data.EquipoDatos;
import Modelo.Data.GuerreroDatos;
import Network.BaseServerClasses.BasicServerClient;
import Network.Server.Server;
import ProjectNetwork.CommandRequestHandler;
import ProjectNetwork.CommandServerSideClient;
import ProjectNetwork.Responses.AvaliableWariorsResponse;
import ProjectNetwork.Responses.MessageResponse;
import Utils.JsonLoader;
import Utils.JsonRanking;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Partida extends Server{

    private static Partida partida;
    public int PERSONAJES_POR_JUGADOR = 5;

    private EjecutorComandos ejecutarComandos = new EjecutorComandos();

    public Equipo[] equipos = new Equipo[2];
    //Referencia al prototypeManager de la libreria de proyecto 1
    private int mutualExit;
    private int inTurn,notInTurn;
    //para leer json personajes
    private JsonLoader json;
    private JsonRanking jsonRanking;
    private ICreator factory1 ;
    private ICreator factory2 ;


    private Partida(int port,CommandRequestHandler requestHandler) throws IOException, ClassNotFoundException {
        super(port,requestHandler);
        ComodinTimer comodinTimer = new ComodinTimer();
        comodinTimer.start();
        factory1 = new WeaponPrototypeFactory();
        factory2 = new CharacterPrototypeFactory();
        cargarFactorys();
        jsonRanking = JsonRanking.getInstance();
    }

    public void addEquipo(Equipo equipo) {
        if(equipos[0] == null)
            this.equipos[0] = equipo;
        else {
            this.equipos[1] = equipo;
            CommandServerSideClient client1 = (CommandServerSideClient) this.getClientes().get(0);
            CommandServerSideClient client2 = (CommandServerSideClient) this.getClientes().get(1);
            client2.setEquipoEnemigo(client1.getEquipo());
            client1.setEquipoEnemigo(client2.getEquipo());
            try {
                sendToClients("Empieza el juego");
                updateUsuarios();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
        CommandServerSideClient client1 = (CommandServerSideClient) getClientes().get(0);
        CommandServerSideClient client2 = (CommandServerSideClient) getClientes().get(1);
        client1.updateData(equipos[0],equipos[1]);
        client2.updateData(equipos[1],equipos[0]);
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

    public ArrayList<Arma> cargarArmas(){
        ArrayList<Arma> armas = new ArrayList<>();
        for (Map.Entry prototype:factory1.getPrototypes().entrySet()) {
            armas.add((Arma) prototype.getValue());
        }
        return armas;

    }

    public ArrayList<Personaje> cargarPersonajes(){
        ArrayList<Personaje> personajes = new ArrayList<>();
        for (Map.Entry prototype:factory2.getPrototypes().entrySet()) {
            personajes.add((Personaje) prototype.getValue());
        }
        return personajes;
    }

    public Equipo crearEquipo(EquipoDatos equipoDatos) throws IOException {
        Equipo equipoClonado = new Equipo();
        ArrayList<Personaje> guerrerosClonados = new ArrayList<>();
        for (GuerreroDatos datosGuerrero: equipoDatos.getDatosGuerreros()) {
            Personaje personajeClonado = (Personaje) factory2.getPrototypeDeepClone(datosGuerrero.getName());
            for (String armaNombre:datosGuerrero.getNombresArmas()) {
                Arma armaClonada = (Arma) factory1.getPrototypeDeepClone(armaNombre);
                personajeClonado.addArmas(armaClonada);
            }
            guerrerosClonados.add(personajeClonado);
        }
        equipoClonado.setGuerreros(guerrerosClonados);

        if (jsonRanking.checkClient(equipoDatos.getNombre())){  //Si el usuario ya existia se lo trae
            equipoClonado.setUsuario(jsonRanking.getUserClient(equipoDatos.getNombre()));
        }
        else{
            jsonRanking.writeJSON(new Usuario(equipoDatos.getNombre()));
            equipoClonado.setUsuario(jsonRanking.getUserClient(equipoDatos.getNombre()));
        }
        addEquipo(equipoClonado);
        return equipoClonado;
    }

    public ArrayList <Integer> generateDamages(){
        ArrayList<Integer> damages = new ArrayList<>();
        for (int i=0; i<10; i++){
            int numero = (int) (Math.random() * 40 + 20);
            damages.add(numero);
        }
        return damages;
    }


}
