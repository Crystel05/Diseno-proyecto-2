package Controller;

import ProjectNetwork.ClientTypes.CommandGameClient;
import CommandPattern.Enumerable.CommandsE;
import ProjectNetwork.Requests.AvaliableWariorsRequest;
import ProjectNetwork.Requests.CommandRequest;
import ProjectNetwork.Requests.ConnectRequest;

import java.io.IOException;

public class ControladorPantalla {
    //Tiene el cliente y la pantalla
    CommandGameClient client;
    static ControladorPantalla controladorPantalla;
    //Guerrero[] guerreros;

    public static ControladorPantalla getInstance() {
        return controladorPantalla==null?new ControladorPantalla():controladorPantalla;
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


    public void requestCommand(String key,String[] params) throws IOException, ClassNotFoundException {
        CommandsE commandKey = CommandsE.valueOf(key.toUpperCase());
        client.request(new CommandRequest(commandKey,params,client.getClientId()));
    }

    public void connectionRequest() throws IOException, ClassNotFoundException {
        client.request(new ConnectRequest());//Asegurarme que el response de esta conexion me de todos los datos que necesito para la pantalla.
    }


//    public void setAvaliableWariors(Guerrero[] guerreros) {
//        this.guerreros = guerreros;
//    }
}
