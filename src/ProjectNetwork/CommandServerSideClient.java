package ProjectNetwork;

import Modelo.AttackInfo;
import Modelo.Equipo;
import Network.BaseServerClasses.BasicServerClient;
import Network.Response.IResponse;
import Network.Response.ISendResponse;
import ProjectNetwork.Responses.AttackInfoResponse;
import ProjectNetwork.Responses.MessageResponse;
import ProjectNetwork.Responses.UpdateResponse;

import java.io.IOException;

public class CommandServerSideClient extends BasicServerClient implements ISendResponse {

    Equipo equipoCliente;
    Equipo equipoEnemigoCliente;

    public CommandServerSideClient(int objectId) {
        super(objectId);
    }

    @Override
    public void sendResponse(IResponse response) throws IOException {
        System.out.println("Enviando respueta...");
        this.getResponseSender().sendResponse(response);
    }

    public void updateData(Equipo equipo,Equipo equipoEnemigo) throws IOException {
        sendResponse(new UpdateResponse(equipo,equipoEnemigo));
    }

    public void sendMessage(String string) throws IOException {
        sendResponse(new MessageResponse(string));
    }

    public void setEquipo(Equipo equipoElegido) {
        this.equipoCliente = equipoElegido;
    }

    public void setEquipoEnemigo(Equipo equipoEnemigo){
        this.equipoEnemigoCliente = equipoEnemigo;
    }

    public Equipo getEquipo() {
        return equipoCliente;
    }

    public void sendAttackInfo(AttackInfo attackInfo) throws IOException {
        sendResponse(new AttackInfoResponse(attackInfo));
    }
}
