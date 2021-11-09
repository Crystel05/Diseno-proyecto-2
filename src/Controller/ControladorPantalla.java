package Controller;

import CommandPattern.Enumerable.CommandsE;
import Network.Client.Client;
import ProjectNetwork.Requests.CommandRequest;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorPantalla {
    //Tiene el cliente y la pantalla
    Client client;

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
    public void attackCommand(String[] params) throws IOException, ClassNotFoundException {
        client.request(new CommandRequest(CommandsE.ATACK,params,client.getClientId()));
    }

}
