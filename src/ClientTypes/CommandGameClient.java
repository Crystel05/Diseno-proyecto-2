package ClientTypes;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Equipo;
import Modelo.Usuario;
import Network.Client.Client;
import Network.Response.IHandleResponse;
import ProjectNetwork.Requests.CommandRequest;

import java.io.IOException;
import java.util.ArrayList;

public class CommandGameClient  extends Client {

    //Datos del cliente
    Equipo equipo;//Cuando le seteo el equipo? El usuario viene del servidor con el equipo//Se actualiza despupes de cada command

    public CommandGameClient(String host, int port, IHandleResponse responseHandler) throws IOException, ClassNotFoundException {
        super(host, port, responseHandler);
    }

    //Vienen los request desde el controlador
    public void request(CommandRequest commandRequest) throws IOException {
        requestSender.sendRequest(commandRequest);
    }

}
