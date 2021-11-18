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

    Equipo equipo;
    Equipo equipoEnemigo;

    public CommandServerSideClient(int objectId) {
        super(objectId);
    }

    @Override
    public void sendResponse(IResponse response) throws IOException {
        System.out.println("Enviando respueta...");
        this.getResponseSender().sendResponse(response);
    }

    public void updateData(Equipo equipo) throws IOException {
        sendResponse(new UpdateResponse(equipo,equipoEnemigo));
    }

    public void sendMessage(String string) throws IOException {
        sendResponse(new MessageResponse(string));
    }

    public void setEquipo(Equipo equipoElegido) {
        this.equipo = equipoElegido;
    }

    public void setEquipoEnemigo(Equipo equipoEnemigo){
        this.equipoEnemigo = equipoEnemigo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void sendAttackInfo(AttackInfo attackInfo) throws IOException {
        sendResponse(new AttackInfoResponse(attackInfo));
    }
}
