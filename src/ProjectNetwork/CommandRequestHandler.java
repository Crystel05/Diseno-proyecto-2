package ProjectNetwork;

import CommandPattern.CommandManager;
import CommandPattern.Enumerable.CommandsE;
import CommandPattern.ICommand;
import Network.Request.IHandleRequest;
import Network.Request.IRequest;
import Network.Request.ISendRequest;
import Network.Server.ServerRequestHandler;
import ProjectNetwork.Requests.CommandRequest;
import ProjectNetwork.Requests.ConnectRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;
import ProjectNetwork.Responses.MessageResponse;

import java.io.IOException;
import java.util.ArrayList;

public class CommandRequestHandler implements IHandleRequest {
    @Override
    public void parseRequest(IRequest request, ServerRequestHandler handler) throws IOException, ClassNotFoundException {
        GameRequestTypes requestType = (GameRequestTypes) request.getType();
        switch (requestType){
            case COMMAND:
                CommandsE commandName = ((CommandRequest) request).getCommandType();//En nuestro caso tiene un enumarable que se llama tipo
                String[] commandArgs = ((CommandRequest) request).getParams();//Devuelve el otro resto
                ICommand command = CommandManager.getCommand(commandName);//Con este le pide al manager
                command.execute(commandArgs);//Aca se hace el execute y pasa al Game
                break;
            case CONNECT:
                int id = handler.getClientes().size();
                handler.addToClients(new CommandServerSideClient(id));
                handler.getResponseSender().sendResponse(new MessageResponse("Successful connection"));
                break;
            default:
                System.out.println("Not known request");
                break;
        }

    }

}
