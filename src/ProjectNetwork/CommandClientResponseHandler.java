package ProjectNetwork;

import Controller.ControladorPantalla;
import Modelo.AttackInfo;
import Network.Client.ClientResponseHandler;
import Network.Response.IHandleResponse;
import Network.Response.IResponse;
import ProjectNetwork.ClientTypes.CommandGameClient;
import ProjectNetwork.Responses.*;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

import java.io.IOException;
import java.util.ArrayList;

public class CommandClientResponseHandler implements IHandleResponse {
    @Override
    public void parseResponse(IResponse request, ClientResponseHandler handler) throws IOException, ClassNotFoundException {
        GameResponsesType type = (GameResponsesType) request.getType();
        CommandGameClient client = (CommandGameClient) handler.getClient();
        switch (type){
            case CONNECT:
                ConnectResponse commandRequest = (ConnectResponse) request;
                System.out.println(commandRequest.clientId);
                client.setClientId(commandRequest.clientId);
                System.out.println("Me conecto");
                break;
            case UPDATE:
                UpdateResponse updateResponse = (UpdateResponse) request;
                client.setEquipo(updateResponse.equipo);
                client.setEquipoEnemigo(updateResponse.equipoEnemigo);
                break;
            case MESSAGE:
                MessageResponse messageResponse = (MessageResponse) request;
                System.out.println(messageResponse.content);
                ControladorPantalla.getInstance().getPantallaJugador().getNotificaciones().getItems().add(messageResponse.content);
                break;
            case SENDWARIORS:
                AvaliableWariorsResponse avaliableWariorsRequest = (AvaliableWariorsResponse) request;
                System.out.println("Me llegan guerreros");
                ControladorPantalla.getInstance().setAvaliableWariors(avaliableWariorsRequest.guerreros);
                break;
            case SENDWEAPONS:
                AvaliableWeaponsResponse avaliableWeaponsResponse = (AvaliableWeaponsResponse) request;
                System.out.println("Me llegan armas");
                ControladorPantalla.getInstance().setAvaliableWeapons(avaliableWeaponsResponse.armasDisponibles);
                break;
            case ATTACKINFO:  //para el atacado
                AttackInfoResponse attackInfoResponse = (AttackInfoResponse) request;
                AttackInfo atack = attackInfoResponse.attackInfo;
                ControladorPantalla.getInstance().getPantallaJugador().setAtaques(atack);
                break;
            case DEALEADDAMAGE: //para el que ataca
                DanoHechoResponse danoHechoResponse = (DanoHechoResponse) request;
                //System.out.println(danoHechoResponse.personaje.getName());
                ControladorPantalla.getInstance().getPantallaJugador().setDanoHecho(danoHechoResponse);
            default:
                break;
        }
    }
}
