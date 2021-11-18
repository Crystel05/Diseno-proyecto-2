package ProjectNetwork.ClientTypes;

import Modelo.Equipo;
import Network.Client.Client;
import Network.Response.IHandleResponse;
import ProjectNetwork.Requests.CommandRequest;

import java.io.IOException;

public class CommandGameClient  extends Client {

    //Datos del cliente
    Equipo equipo;//Cuando le seteo el equipo? El usuario viene del servidor con el equipo//Se actualiza despupes de cada command
    Equipo equipoEnemigo;

    public CommandGameClient(String host, int port, IHandleResponse responseHandler) throws IOException, ClassNotFoundException {
        super(host, port, responseHandler);
    }

    //Vienen los request desde el controlador
    public void request(CommandRequest commandRequest) throws IOException {
        requestSender.sendRequest(commandRequest);
    }

    public void setEquipo(Equipo equipo){
        this.equipo = equipo;
    }

    public Equipo getEquipo(){
        return equipo;
    }

    public void setEquipoEnemigo(Equipo equipo){
        this.equipoEnemigo = equipo;
    }

    public Equipo getEquipoEnemigo(){
        return equipoEnemigo;
    }

}
