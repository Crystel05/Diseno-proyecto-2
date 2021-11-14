package Controller;

import Model.Weapon;
import Modelo.Arma;
import Modelo.Data.EquipoDatos;
import Modelo.Personaje;
import ProjectNetwork.ClientTypes.CommandGameClient;
import CommandPattern.Enumerable.CommandsE;
import ProjectNetwork.CommandClientResponseHandler;
import ProjectNetwork.Requests.CommandRequest;
import ProjectNetwork.Requests.ConnectRequest;
import ProjectNetwork.Requests.SelectedWarriors;
import Vista.InicioJuego;
import Vista.PantallaJugador;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorPantalla {
    //Tiene el cliente y la pantalla
    CommandGameClient client;
    static ControladorPantalla controladorPantalla;
    ArrayList<Personaje> personajes;
    ArrayList<Arma> armas;
    InicioJuego inicioJuego;


    public static ControladorPantalla getInstance() {
        if(controladorPantalla == null){
            controladorPantalla = new ControladorPantalla();
        }
        return controladorPantalla;
    }

    //Escribe los comandos desde pantalla.
    //Los comandos strings.
    //Llegan al controlador

    //Pantalla

    //Ejemplo de un comando
    //attack jack pistola
    //params = [jack,pistola]
    //request = new CommandRequest(CommandsE.ATTACK,params)

    //Desde pantalla se le pasan los parametros como una lista.
    //Desde pantalla se le pasan uno a uno los paramentros.

    public void setInicioJuego(InicioJuego inicioJuego){
        this.inicioJuego = inicioJuego;
        System.out.println("Se liga pantalla y controlador");
    }


    public void requestCommand(String key,String[] params) throws IOException, ClassNotFoundException {
        CommandsE commandKey = CommandsE.valueOf(key.toUpperCase());
        client.request(new CommandRequest(commandKey,params,client.getClientId()));
    }

    public void connectionRequest() throws IOException, ClassNotFoundException {
        client = new CommandGameClient("localhost",6000,new CommandClientResponseHandler());
        client.request(new ConnectRequest());//Asegurarme que el response de esta conexion me de todos los datos que necesito para la pantalla.
    }

    public void setAvaliableWariors(ArrayList<Personaje> guerreros) {
        this.personajes = guerreros;
    }

    public void setAvaliableWeapons(ArrayList<Arma> armasDisponibles) {
        this.armas = armasDisponibles;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;  //
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }

    public void setInitialData() {//Esto actualiza la pantalla cuando le llega el request.
        inicioJuego.cargarDatosIniciales();
    }

    public void enviarEquipoElegido(EquipoDatos equipoDatos) throws IOException, ClassNotFoundException {
        client.request(new SelectedWarriors(equipoDatos));
    }


}
