package ProjectNetwork;

import Modelo.Equipo;
import Network.BaseServerClasses.BasicServerClient;
import Network.Response.IResponse;
import Network.Response.ISendResponse;
import ProjectNetwork.Responses.MessageResponse;
import ProjectNetwork.Responses.UpdateResponse;

import java.io.IOException;

public class CommandServerSideClient extends BasicServerClient implements ISendResponse {
    public CommandServerSideClient(int objectId) {
        super(objectId);
    }

    @Override
    public void sendResponse(IResponse response) throws IOException {
        this.getResponseSender().sendResponse(response);
    }

    public void updateData(Equipo equipo) throws IOException {
        sendResponse(new UpdateResponse(equipo));
    }

    public void sendMessage(String string) throws IOException {
        sendResponse(new MessageResponse(string));
    }
}
