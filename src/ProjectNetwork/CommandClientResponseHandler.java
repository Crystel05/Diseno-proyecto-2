package ProjectNetwork;

import Controller.ControladorPantalla;
import Network.Client.ClientResponseHandler;
import Network.Response.IHandleResponse;
import Network.Response.IResponse;
import ProjectNetwork.ClientTypes.CommandGameClient;
import ProjectNetwork.Requests.AvaliableWariorsRequest;
import ProjectNetwork.Requests.CommandRequest;
import ProjectNetwork.Requests.ConnectRequest;
import ProjectNetwork.Responses.AvaliableWariorsResponse;
import ProjectNetwork.Responses.ConnectResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

import java.io.IOException;

public class CommandClientResponseHandler implements IHandleResponse {
    @Override
    public void parseResponse(IResponse request, ClientResponseHandler handler) throws IOException, ClassNotFoundException {
        GameResponsesType type = (GameResponsesType) request.getType();
        CommandGameClient client = (CommandGameClient) handler.getClient();
        switch (type){
            case CONNECT:
                ConnectResponse commandRequest = (ConnectResponse) request;
                client.setClientId(commandRequest.clientId);
                break;
            case UPDATE:
                break;
            case MESSAGE:
                break;
            case SENDWARIORS:
                AvaliableWariorsResponse avaliableWariorsRequest = (AvaliableWariorsResponse) request;
                ControladorPantalla.getInstance().setAvaliableWariors(avaliableWariorsRequest.guerreros);
                break;
            default:
                break;
        }
    }
}
